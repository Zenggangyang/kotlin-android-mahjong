package dev.yuriel.kotmahjan.models

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.Mentsu

/**
 * Created by yuriel on 7/23/16.
 */

open class Mahjong4jException(message: String) : Exception(message)

class HandsOverFlowException : Mahjong4jException("多牌です")

/**
 * 面子の組が和了の形になっていない場合に投げられます
 */
class IllegalMentsuSizeException(
        /**
         * @return 誤っていると判定されている面子の組を返します
         */
        val mentsuList: List<Mentsu>) : Mahjong4jException("面子の組が和了の形になっていません") {

    val advice: String
        get() = "面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが" + mentsuList.size + "個の面子が見つかりました"
}

class IllegalShuntsuIdentifierException(private val tile: Hai) : Mahjong4jException("順子識別牌としてありえない牌を検出しました") {

    val advice: String
        get() {
            val entry = "${tile.id}を識別牌として保存しようとしました\n"
            if (tile.num == -1) {
                return entry + "字牌は順子になりえません"
            }
            return entry + "2番目の牌を順子識別牌とするため、1・9牌は識別牌になりえません"
        }
}

class MahjongTileOverFlowException(//Tile.code
        private val code: Int, //何枚見つかり不正なのか
        private val num: Int) : Mahjong4jException("麻雀の牌は4枚までしかありません") {

    val advice: String
        get() = "(code = " + code + ")が" + num + "枚見つかりました"
}

class NoSuchTileException(private val id: Int): Mahjong4jException("牌は見つかりません") {
    val advice: String = "id = " + id + "は 0 から 33 までの数字でなければなりません"
}