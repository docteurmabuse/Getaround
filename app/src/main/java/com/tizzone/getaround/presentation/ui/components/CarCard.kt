package com.tizzone.getaround.presentation.ui.components

import android.graphics.drawable.Drawable
import android.widget.RatingBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.tizzone.getaround.R
import com.tizzone.getaround.domain.model.Car
import com.tizzone.getaround.presentation.theme.GetaroundTheme
import com.tizzone.getaround.utils.FAKE_CAR

@Composable
fun CarCard(
    car: Car,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            // The space between each card and the other
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
    ) {

        ConstraintLayout {
            val (image, model, price, carRating, average) = createRefs()
            CoilImage(
                imageModel = car.owner.pictureUrl,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(150.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 250),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = painterResource(id = R.drawable.ic_baseline_car_24),
                // shows an error ImageBitmap when the request failed.
                error = painterResource(id = R.drawable.ic_baseline_car_24),
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(model) {
                        top.linkTo(image.bottom, margin = 0.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                    },
                text = car.model,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(price) {
                        top.linkTo(image.bottom)
                        end.linkTo(parent.end)
                    },
                text = "${car.pricePerDay}€/j",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            val ratingBar =
                RatingBar(LocalContext.current, null, android.R.attr.ratingBarStyleSmall)
            val star: Drawable = ratingBar.progressDrawable
            DrawableCompat.setTint(star, MaterialTheme.colorScheme.tertiary.toArgb())
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
                        start.linkTo(parent.start)
                    },
            )
            car.rating?.let {
                Text(
                    modifier = Modifier
                        .constrainAs(average) {
                            top.linkTo(carRating.top)
                            bottom.linkTo(carRating.bottom)
                            start.linkTo(carRating.end, 8.dp)
                        },
                    text = it.count.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarCardPreview() {
    GetaroundTheme {
        CarCard(
            car = FAKE_CAR,
            modifier = Modifier,
            onClick = {}
        )
    }
}
