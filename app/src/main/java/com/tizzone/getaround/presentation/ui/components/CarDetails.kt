package com.tizzone.getaround.presentation.ui.components

import android.graphics.drawable.Drawable
import android.widget.RatingBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.skydoves.landscapist.coil.CoilImage
import com.tizzone.getaround.R
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.presentation.theme.GetaroundTheme
import com.tizzone.getaround.utils.FAKE_CAR

@Composable
fun CarDetails(
    car: Car,
    navigateUp: () -> Unit,
) {
    ConstraintLayout {
        val (image, model, price, carRating, average, ownerTitle, ownerName, ownerImage, ownerRating) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(280.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .zIndex(2f)
                    .clickable {
                        navigateUp()
                    },
                tint = MaterialTheme.colorScheme.primary
            )
            CoilImage(
                imageModel = car.owner.pictureUrl,
                contentDescription = car.model,
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            modifier = Modifier
                .constrainAs(model) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            text = car.model,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(price) {
                    top.linkTo(image.bottom)
                    end.linkTo(parent.end)
                },
            text = "${car.pricePerDay}€/j",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        val ratingBar =
            RatingBar(
                LocalContext.current,
                null,
                androidx.constraintlayout.widget.R.attr.ratingBarStyleSmall
            )
        if (ratingBar.progressDrawable != null) {
            val star: Drawable = ratingBar.progressDrawable
            DrawableCompat.setTint(star, MaterialTheme.colorScheme.tertiary.toArgb())
        }
        ratingBar.apply {
            setIsIndicator(true)
            numStars = 5
            rating = car.rating?.average?.toFloat() ?: 0F
        }

        AndroidView(
            { ratingBar },
            modifier = Modifier
                .constrainAs(carRating) {
                    top.linkTo(model.bottom)
                    start.linkTo(parent.start, 20.dp)
                },
        )
        car.rating?.let {
            Text(
                modifier = Modifier
                    .constrainAs(average) {
                        top.linkTo(carRating.top)
                        start.linkTo(carRating.end, 8.dp)
                    },
                text = it.count.toString(),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier
                .constrainAs(ownerTitle) {
                    top.linkTo(carRating.bottom, 50.dp)
                    start.linkTo(parent.start, 20.dp)
                },
            text = stringResource(id = R.string.owner),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        CoilImage(
            imageModel = car.owner.pictureUrl,
            contentDescription = car.owner.name,
            modifier = Modifier
                .constrainAs(ownerImage) {
                    top.linkTo(ownerTitle.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                }
                .height(50.dp)
                .width(50.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier
                .constrainAs(ownerName) {
                    top.linkTo(ownerImage.top)
                    start.linkTo(ownerImage.end, 20.dp)
                },
            text = car.owner.name,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        val ratingBarOwner =
            RatingBar(
                LocalContext.current,
                null,
                androidx.constraintlayout.widget.R.attr.ratingBarStyleSmall
            )
        if (ratingBarOwner.progressDrawable != null) {
            val starOwner: Drawable = ratingBarOwner.progressDrawable
            DrawableCompat.setTint(starOwner, MaterialTheme.colorScheme.tertiary.toArgb())
        }
        ratingBarOwner.apply {
            setIsIndicator(true)
            numStars = 5
            rating = car.owner.rating?.average?.toFloat() ?: 0F
        }

        AndroidView(
            { ratingBarOwner },
            modifier = Modifier
                .constrainAs(ownerRating) {
                    top.linkTo(ownerName.bottom, 10.dp)
                    bottom.linkTo(ownerImage.bottom)
                    start.linkTo(ownerImage.end, 20.dp)
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CarDetailsPreview() {
    GetaroundTheme {
        CarDetails(
            car = FAKE_CAR,
            navigateUp = {}
        )
    }
}
