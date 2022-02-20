package com.tizzone.getaround.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.domain.model.Owner
import com.tizzone.getaround.domain.model.Rating
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class CarDtoModel(
    @field:SerializedName("owner")
    val owner: OwnerDtoModel? = null,

    @field:SerializedName("rating")
    val rating: RatingDtoModel? = null,

    @field:SerializedName("model")
    val model: String? = null,

    @field:SerializedName("price_per_day")
    val pricePerDay: Int? = null,

    @field:SerializedName("brand")
    val brand: String? = null
) : Parcelable {
    fun toDomain(): Car {
        return Car(
            id = UUID.randomUUID().toString(),
            owner = OwnerDtoModel.toDomain(owner!!),
            rating = rating?.let { RatingDtoModel.toDomain(it) },
            model = model!!,
            pricePerDay = pricePerDay!!,
            brand = brand!!
        )
    }
}

@Parcelize
data class RatingDtoModel(
    @field:SerializedName("average")
    val average: Double? = null,

    @field:SerializedName("count")
    val count: Int? = null
) : Parcelable {
    companion object {
        fun toDomain(dtoModel: RatingDtoModel): Rating {
            return Rating(
                average = dtoModel.average,
                count = dtoModel.count
            )
        }
    }
}

@Parcelize
data class OwnerDtoModel(
    @field:SerializedName("picture_url")
    val pictureUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("rating")
    val rating: RatingDtoModel? = null
) : Parcelable {
    companion object {
        fun toDomain(dtoModel: OwnerDtoModel): Owner {
            return Owner(
                pictureUrl = dtoModel.pictureUrl,
                name = dtoModel.name!!,
                rating = dtoModel.rating?.let { RatingDtoModel.toDomain(it) }
            )
        }
    }
}
