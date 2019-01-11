package com.mygdx.game

import com.badlogic.gdx.graphics.Texture



class Media {
    companion object {
        lateinit var grass_01: Texture
        lateinit var grass_02: Texture
        lateinit var grass_03: Texture
        lateinit var grass_04: Texture
        lateinit var grass_left: Texture
        lateinit var grass_right: Texture
        lateinit var grass_left_upper_edge: Texture
        lateinit var grass_right_upper_edge: Texture
        lateinit var grass_top: Texture
        lateinit var grass_top_right: Texture
        lateinit var grass_top_left: Texture
        lateinit var water_01: Texture
        lateinit var water_02: Texture
        lateinit var water_03: Texture
        lateinit var water_04: Texture
        lateinit var cliff: Texture
        lateinit var water: Texture
        lateinit var hero: Texture

        fun load_assets() {
            // Source https://opengameart.org/content/micro-tileset-overworld-and-dungeon
            // Example Map: http://opengameart.org/sites/default/files/styles/watermarked/public/Render_0.png
            grass_01 = Texture("8x8/grass/grass_01.png")
            grass_02 = Texture("8x8/grass/grass_02.png")
            grass_03 = Texture("8x8/grass/grass_03.png")
            grass_04 = Texture("8x8/grass/grass_04.png")

            grass_left = Texture("8x8/grass/right_grass_edge.png")
            grass_right = Texture("8x8/grass/left_grass_edge.png")

            grass_left_upper_edge = Texture("8x8/grass/left_upper_edge.png")
            grass_right_upper_edge = Texture("8x8/grass/right_upper_edge.png")

            grass_top = Texture("8x8/grass/top.png")
            grass_top_right = Texture("8x8/grass/top_right.png")
            grass_top_left = Texture("8x8/grass/top_left.png")

            water_01 = Texture("8x8/water/water_01.png")
            water_02 = Texture("8x8/water/water_02.png")
            water_03 = Texture("8x8/water/water_03.png")
            water_04 = Texture("8x8/water/water_04.png")
            cliff = Texture(("8x8/cliff.png"))
            hero = Texture("hero.png")
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

}