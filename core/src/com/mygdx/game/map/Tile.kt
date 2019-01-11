package com.mygdx.game.map

import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Entity
import com.mygdx.game.Enums.TILETYPE


class Tile(x: Float, y: Float, var size: Float, var tileType: TILETYPE) : Entity() {
    var row: Int = 0
    var col: Int = 0
    var code: String
    var secondary_texture: Texture? = null

    val is_grass: Boolean
        get() = tileType === TILETYPE.GRASS

    val is_water: Boolean
        get() = tileType === TILETYPE.WATER

    val is_cliff: Boolean
        get() = tileType === TILETYPE.CLIFF

    val is_passable: Boolean
        get() = !is_water && !is_cliff

    val is_not_passable: Boolean
        get() = !is_passable

    private fun isAllWater(): Boolean {
        return code == "000000000"
    }

    fun notIsAllWater(): Boolean {
        return !isAllWater()
    }

    init {
        pos.x = x * size
        pos.y = y * size
        this.col = x.toInt()
        this.row = y.toInt()
        this.code = ""
    }

    fun details(): String {
        return "x: " + pos.x + " y: " + pos.y + " row: " + row + " col: " + col + " code: " + code + " type: " + tileType.toString()
    }
}