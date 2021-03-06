/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.collections.Mentsu
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 混一色判定
 * 萬子、索子、筒子のどれか一種と、字牌のみで構成される場合成立
 */
fun 混一色Impl(s: MentsuSupport): Boolean {
    var hasJihai = false
    var type: HaiType? = null

    fun hasOnlyOneType(mentsu: Mentsu): Boolean {
        if (mentsu.getHai()?.num == -1) {
            hasJihai = true
        } else if (type == null) {
            type = mentsu.getHai()?.type
        } else if (type != mentsu.getHai()?.type) {
            return false
        }
        return true
    }

    for (mentsu in s.allMentsu) {
        if (!hasOnlyOneType(mentsu)) {
            return false
        }
    }

    return hasJihai
}