package com.mygdx.game.map

class Chunk(internal var number_rows: Int, internal var number_cols: Int, internal var tile_size: Int) {
    // Tiles are split into arrays of rows
    var tiles = ArrayList<ArrayList<Tile>>()

    init {
        tiles = ArrayList()
    }

    fun get_tile(row: Int, col: Int): Tile? {
        println("Row: $row Col: $col")
        val chunk_row: ArrayList<Tile>?
        if (tiles.size > row && row >= 0) {
            chunk_row = tiles[row]

            if (chunk_row != null && chunk_row.size > col && col >= 0) {
                return chunk_row[col]
            }
        }
        return null
    }

    fun get_tile_code(row: Int, col: Int): String {
        val tile: Tile

        val chunk_row: ArrayList<Tile>?
        if (tiles.size > row && row >= 0) {
            chunk_row = tiles[row]

            if (chunk_row != null && chunk_row.size > col && col >= 0) {
                tile = chunk_row[col]
                return if (tile.is_grass) "1" else "0"
            }
        }
        return "0"
    }

}