package com.mygdx.game.map

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.BodyDef
import com.mygdx.game.Entity
import com.mygdx.game.Enums.TILETYPE
import com.mygdx.game.Media.Companion.cliff
import com.mygdx.game.Media.Companion.grass_01
import com.mygdx.game.Media.Companion.grass_02
import com.mygdx.game.Media.Companion.grass_03
import com.mygdx.game.Media.Companion.grass_04
import com.mygdx.game.Media.Companion.grass_left
import com.mygdx.game.Media.Companion.grass_left_upper_edge
import com.mygdx.game.Media.Companion.grass_right
import com.mygdx.game.Media.Companion.grass_right_upper_edge
import com.mygdx.game.Media.Companion.grass_top
import com.mygdx.game.Media.Companion.grass_top_left
import com.mygdx.game.Media.Companion.grass_top_right
import com.mygdx.game.Media.Companion.water_01
import com.mygdx.game.Media.Companion.water_02
import com.mygdx.game.Media.Companion.water_03
import com.mygdx.game.Media.Companion.water_04
import com.mygdx.game.box2d.Box2DHelper
import com.mygdx.game.box2d.Box2DWorld
import java.util.*




class Island(box2D: Box2DWorld) {
    // TILES
    internal lateinit var centre_tile: Tile
    internal lateinit var clicked_tile: Tile

    // CHUNKS TODO: Add multiple chunks
    // public Map<Integer, ArrayList<Chunk> chunks = new Map<Integer, ArrayList<Chunk>();

    // ONE CHUNK
    lateinit var chunk: Chunk
    internal var entities = ArrayList<Entity>()

    // TRACK CLICK
    internal var current_tile_no: Int = 0
    internal var current_col: Int = 0
    internal var current_row: Int = 0

    // Arrays for mapping code to texture
    internal var a_grass_left = arrayOf("001001001", "001001001", "001001000", "000001001")
    internal var a_grass_right = arrayOf("100100100", "100100000", "000100100")
    internal var a_grass_r_end = arrayOf("100000000")
    internal var a_grass_l_end = arrayOf("001000000")
    internal var a_grass_top = arrayOf("000000111", "000000011", "000000110")
    internal var a_grass_top_right = arrayOf("000000100")
    internal var a_grass_top_left = arrayOf("000000001")

    init {
        setup_tiles()
        code_tiles()
        generateHitboxes(box2D)
    }

    private fun generateHitboxes(box2D: Box2DWorld) {
        for (row in chunk.tiles) {
            for (tile in row) {
                if (tile.is_not_passable && tile.notIsAllWater()) {
                    Box2DHelper.createBody(box2D.world, chunk.tile_size.toFloat(), chunk.tile_size.toFloat(), tile.pos, BodyDef.BodyType.StaticBody)
                }
            }
        }
    }


    private fun setup_tiles() {
        chunk = Chunk(33, 33, 8)

        var current_row = 0
        val rng_w = MathUtils.random(5, 8)
        val rng_h = MathUtils.random(5, 8)

        val centre_tile_row = chunk.number_rows / 2
        val centre_tile_col = chunk.number_cols / 2
        val first_tile_row = centre_tile_row - rng_h

        val max_row = centre_tile_row + rng_h
        val min_row = centre_tile_row - rng_h
        val max_col = centre_tile_col + rng_w
        val min_col = centre_tile_col - rng_w

        // CHUNK ROW
        var chunk_row = ArrayList<Tile>()

        // If number of tiles is needed.
        // int num_tiles = ((max_col - min_col)-1) * ((max_row - min_row)-1);

        for (row in 0 until chunk.number_rows) {
            for (col in 0 until chunk.number_cols) {
                // Create TILE
                val tile = Tile(col.toFloat(), row.toFloat(), chunk.tile_size.toFloat(), TILETYPE.WATER)
                tile.texture = random_water()

                // Make a small island
                if (row > min_row && row < max_row && col > min_col && col < max_col) {
                    tile.texture = random_grass()
                    tile.tileType = TILETYPE.GRASS

                    if (row == first_tile_row + 1) {
                        tile.texture = cliff
                        tile.tileType = TILETYPE.CLIFF
                    } else {
                        // Chance to add trees etc
                    }
                }

                // ADD TILE TO CHUNK
                if (current_row == row) {
                    // Add tile to current row
                    chunk_row.add(tile)

                    // Last row and column?
                    if (row == chunk.number_rows - 1 && col == chunk.number_cols - 1) {
                        chunk.tiles.add(chunk_row)
                    }
                } else {
                    // New row
                    current_row = row

                    // Add row to chunk
                    chunk.tiles.add(chunk_row)

                    // Clear chunk row
                    chunk_row = ArrayList()

                    // Add first tile to the new row
                    chunk_row.add(tile)
                }
            }
        }

        // Set centre tile for camera positioning
        centre_tile = chunk.get_tile(centre_tile_row, centre_tile_col)!!
    }

    private fun update_image(tile: Tile) {
        // Secondary Texture is to add edges to tiles
        // TODO: Add array of textures per tile

        when {
            (a_grass_left).contains(tile.code) -> tile.secondary_texture = grass_left
            (a_grass_right).contains(tile.code) -> tile.secondary_texture = grass_right
            (a_grass_r_end).contains(tile.code) -> tile.secondary_texture = grass_left_upper_edge
            (a_grass_l_end).contains(tile.code) -> tile.secondary_texture = grass_right_upper_edge
            (a_grass_top).contains(tile.code) -> tile.secondary_texture = grass_top
            (a_grass_top_right).contains(tile.code) -> tile.secondary_texture = grass_top_right
            (a_grass_top_left).contains(tile.code) -> tile.secondary_texture = grass_top_left
        }
    }

    private fun random_grass(): Texture {
        val grass: Texture

        val tile = MathUtils.random(20)
        when (tile) {
            1 -> grass = grass_01
            2 -> grass = grass_02
            3 -> grass = grass_03
            4 -> grass = grass_04
            else -> grass = grass_01
        }

        return grass
    }

    private fun random_water(): Texture {
        val water: Texture

        val tile = MathUtils.random(20)
        when (tile) {
            1 -> water = water_01
            2 -> water = water_02
            3 -> water = water_03
            4 -> water = water_04
            else -> water = water_01
        }

        return water
    }

    private fun code_tiles() {
        // Loop all tiles and set the initial code

        // 1 CHUNK ONLY ATM
        for (row in chunk.tiles) {
            for (tile in row) {
                // Check all surrounding tiles and set 1 for pass 0 for non pass
                // 0 0 0
                // 0 X 0
                // 0 0 0

                val rows = intArrayOf(1, 0, -1)
                val cols = intArrayOf(-1, 0, 1)

                for (r in rows) {
                    for (c in cols) {
                        tile.code += chunk.get_tile_code(tile.row + r, tile.col + c)
                        update_image(tile)
                    }
                }
            }
        }
    }

    fun dispose() {
        grass_01.dispose()
        grass_02.dispose()
        grass_03.dispose()
        grass_04.dispose()
        grass_left.dispose()
        grass_right.dispose()
        grass_left_upper_edge.dispose()
        grass_right_upper_edge.dispose()
        grass_top.dispose()
        grass_top_right.dispose()
        grass_top_left.dispose()
        water_01.dispose()
        water_02.dispose()
        water_03.dispose()
        water_04.dispose()
        cliff.dispose()
    }
}