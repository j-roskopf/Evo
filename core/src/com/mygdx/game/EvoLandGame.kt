package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.box2d.Box2DWorld
import com.mygdx.game.map.Island

class EvoLandGame : ApplicationAdapter() {
    lateinit var batch: SpriteBatch
    lateinit var img: Texture
    lateinit var camera: OrthographicCamera
    lateinit var control: Control

    // Display Size
    private var displayW: Int = 0
    private var displayH: Int = 0

    // Temp x and y co-ords
    internal var x: Int = 0
    internal var y: Int = 0

    lateinit var hero: Hero

    // Island
    lateinit var island: Island

    lateinit var box2D: Box2DWorld

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")

        Media.load_assets()

        // CAMERA
        displayW = Gdx.graphics.width
        displayH = Gdx.graphics.height

        // For 800x600 we will get 266*200
        val h = (displayH / Math.floor((displayH / 160).toDouble())).toInt()
        val w = (displayW / (displayH / (displayH / Math.floor((displayH / 160).toDouble())))).toInt()

        camera = OrthographicCamera(w.toFloat(), h.toFloat())
        camera.zoom = .4f

        // Used to capture Keyboard Input
        control = Control(displayW, displayH, camera)
        Gdx.input.inputProcessor = control

        box2D = Box2DWorld()

        island = Island(box2D)

        hero = Hero(island.centre_tile.pos, box2D)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // GAME LOGIC
        // Reset the direction values
        hero.update(control)
        camera.position.lerp(hero.pos, .1f)

        camera.update()

        // GAME DRAW
        batch.projectionMatrix = camera.combined
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)

        batch.begin()


        // Draw all tiles in the chunk / chunk rows
        for (row in island.chunk.tiles) {
            for (tile in row) {
                batch.draw(tile.texture, tile.pos.x, tile.pos.y, tile.size, tile.size)
                if (tile.secondary_texture != null) batch.draw(tile.secondary_texture, tile.pos.x, tile.pos.y, tile.size, tile.size)
            }
        }

        hero.draw(batch)

        batch.end()

        // call tick method to draw debug lines
        // pass in control to check it debug is true
        box2D.tick(camera, control)
    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
    }
}
