package com.jio.glass.domain.entity

import android.content.res.Resources
import androidx.annotation.DrawableRes
import com.jio.glass.R


fun flowerList(resources: Resources): List<Product> {
    return listOf(
        Product(
            id = 1,
            name = resources.getString(R.string.jio_cinema),
            image = R.drawable.jio_cinema
        ),
        Product(
            id = 2,
            name = resources.getString(R.string.jio_tv),
            image = R.drawable.jio_tv
        ),
        Product(
            id = 3,
            name = resources.getString(R.string.jio_saavn),
            image = R.drawable.jio_saavn
        ),
        Product(
            id = 4,
            name = resources.getString(R.string.ar_vidhya),
            image = R.drawable.ar_vidhya
        ),
        Product(
            id = 5,
            name = resources.getString(R.string.jio_cinema),
            image = R.drawable.jio_cinema
        ),
        Product(
            id = 6,
            name = resources.getString(R.string.jio_tv),
            image = R.drawable.jio_tv
        ),
        Product(
            id = 7,
            name = resources.getString(R.string.jio_saavn),
            image = R.drawable.jio_saavn
        ),
        Product(
            id = 8,
            name = resources.getString(R.string.jio_meet),
            image = R.drawable.jio_meet
        ),
        Product(
            id = 9,
            name = resources.getString(R.string.reliance_digital),
            image = R.drawable.reliance_digital
        ),
        Product(
            id = 10,
            name = resources.getString(R.string.jio_mart),
            image = R.drawable.jio_mart
        ),
        Product(
            id = 11,
            name = resources.getString(R.string.ar_vidhya),
            image = R.drawable.ar_vidhya
        ),
        Product(
            id = 12,
            name = resources.getString(R.string.hamleys),
            image = R.drawable.hamleys
        ),
        Product(
            id = 13,
            name = resources.getString(R.string.armani),
            image = R.drawable.armani
        ),
        Product(
            id = 14,
            name = resources.getString(R.string.espn),
            image = R.drawable.espn
        ),
        Product(
            id = 15,
            name = resources.getString(R.string.byju),
            image = R.drawable.byju
        ),
        Product(
            id = 16,
            name = resources.getString(R.string.jio_meet),
            image = R.drawable.jio_meet
        ),
        Product(
            id = 17,
            name = resources.getString(R.string.reliance_digital),
            image = R.drawable.reliance_digital
        ),
        Product(
            id = 18,
            name = resources.getString(R.string.jio_mart),
            image = R.drawable.jio_mart
        ),
        Product(
            id = 19,
            name = resources.getString(R.string.ar_vidhya),
            image = R.drawable.ar_vidhya
        ),
        Product(
            id = 20,
            name = resources.getString(R.string.hamleys),
            image = R.drawable.hamleys
        ),
    )
}

data class Product(
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int
)