package com.mygdx.game


import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.BodyDef
import com.mygdx.game.Enums.ENTITYTYPE
import com.mygdx.game.box2d.Box2DHelper
import com.mygdx.game.box2d.Box2DWorld


class Hero(pos: Vector3, box2DWorld: Box2DWorld) : Entity() {

    val _camera_x: Float
        get() = pos.x + width / 2

    val _camera_y: Float
        get() = pos.y + height / 2

    init {
        entityType = ENTITYTYPE.HERO
        width = 8f
        height = 8f
        this.pos.x = pos.x
        this.pos.y = pos.y
        texture = Media.hero

        speed = 60f

        // Create a new Dynamic body
        body = Box2DHelper.createBody(box2DWorld.world, width, height / 2, pos, BodyDef.BodyType.DynamicBody)
    }

    fun update(control: Control) {
        dir_x = 0f
        dir_y = 0f

        if (control.down) dir_y = -1f
        if (control.up) dir_y = 1f
        if (control.left) dir_x = -1f
        if (control.right) dir_x = 1f

        println(speed)

        body.setLinearVelocity(dir_x * speed, dir_y * speed)
        pos.x = body.position.x - width / 2
        pos.y = body.position.y - height / 4
    }

}