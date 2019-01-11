package com.mygdx.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.Body
import com.mygdx.game.Enums.ENTITYTYPE







open class Entity {
    var pos: Vector3 = Vector3()
    lateinit var texture: Texture
    var width: Float = 0.toFloat()
    var height: Float = 0.toFloat()
    lateinit var body: Body
    var dir_x = 0f
    var dir_y = 0f

    var entityType: ENTITYTYPE? = null
    var speed: Float = 0.toFloat()

    fun draw(batch: SpriteBatch) {
        batch.draw(texture, pos.x, pos.y, width, height)
    }
}

