package com.anime.dl.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.anime.dl.databinding.CommonViewEmptyBinding

class EmptyView @JvmOverloads constructor(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private lateinit var binding: CommonViewEmptyBinding

    init {
        binding = CommonViewEmptyBinding.inflate(LayoutInflater.from(context), this, true)
        drawMonke()
    }

    private fun drawMonke() {
        binding.monkeFaceOne.setGlyphStrings(
            arrayOf(
                "M2402 5069 c-866 -72 -1505 -741 -1792 -1876 l-10 -42 -52 27 c-74 38 -161 38 -240 -1 -145 -71 -212 -239 -174 -433 23 -121 75 -273 132 -385 55 -110 89 -163 157 -242 l49 -58 9 -212 c24 -601 143 -990 394 -1286 194 -229 489 -388 865 -465 524 -107 1309 -94 1768 30 382 103 655 289 838 569 62 96 90 151 137 271 97 246 152 577 159 949 l2 140 45 50 c167 188 327 591 308 776 -27 250 -233 392 -430 295 l-47 -23 -10 41 c-238 942 -723 1571 -1380 1790 -137 46 -275 73 -434 86 -141 11 -145 11 -294 -1z m-1917 -1933 c58 -27 128 -99 156 -159 22 -50 25 -127 4 -166 -19 -37 -71 -81 -96 -81 -24 0 -34 26 -44 114 -8 84 -29 154 -54 183 -25 30 -82 63 -108 63 -28 0 -53 10 -53 21 0 18 66 49 105 49 22 0 63 -11 90 -24z m4313 4 c17 -11 32 -24 32 -29 0 -11 -25 -21 -52 -21 -32 0 -91 -38 -116 -74 -21 -30 -34 -82 -59 -231 -10 -61 -34 -70 -82 -29 -43 36 -61 74 -61 131 1 166 216 327 338 253z m-869 -261 c70 -15 112 -46 140 -103 62 -123 28 -374 -104 -784 l-46 -142 37 -91 c20 -50 50 -144 67 -208 28 -106 30 -130 31 -286 1 -149 -2 -180 -22 -248 -96 -331 -371 -571 -827 -722 -255 -85 -533 -131 -722 -121 -212 11 -500 71 -692 143 -320 119 -532 284 -645 503 -61 118 -86 211 -93 351 -9 181 23 353 107 576 l40 106 -46 141 c-132 411 -165 658 -103 781 28 58 60 83 126 101 63 17 2673 20 2752 3z",
                "M1633 2685 c-49 -21 -68 -59 -67 -131 2 -208 225 -343 410 -248 98 50 154 142 154 253 0 65 -20 104 -64 125 -46 22 -383 23 -433 1z",
                "M3052 2683 c-43 -21 -62 -59 -62 -126 0 -77 25 -140 76 -193 145 -149 382 -105 467 85 27 61 29 157 4 195 -32 49 -68 56 -269 56 -149 0 -189 -4 -216 -17z",
                "M2134 1886 c-78 -25 -124 -74 -124 -135 0 -28 9 -43 44 -76 91 -88 249 -83 336 10 19 20 25 38 25 70 0 104 -148 172 -281 131z",
                "M2820 1885 c-48 -15 -105 -63 -116 -97 -26 -83 81 -178 201 -178 93 0 191 64 202 132 16 106 -148 188 -287 143z",
                "M1388 1056 c-55 -15 -78 -31 -78 -57 0 -66 224 -152 501 -193 341 -50 1096 -53 1464 -5 239 31 439 93 508 157 53 50 24 86 -86 105 -52 9 -96 7 -248 -11 -316 -38 -461 -45 -884 -45 -419 -1 -602 9 -885 44 -178 22 -227 23 -292 5z"
            )
        )
            
        val colors = intArrayOf(
            0xff31373d.toInt(),
            0xff31373d.toInt(),
            0xff31373d.toInt(),
            0xff31373d.toInt(),
            0xff31373d.toInt(),
            0xff31373d.toInt()
        )

        binding.monkeFaceOne.setFillColors(colors)
        binding.monkeFaceOne.setTraceColors(colors)
        binding.monkeFaceOne.rebuildGlyphData()
        binding.monkeFaceOne.start()
    }
}
