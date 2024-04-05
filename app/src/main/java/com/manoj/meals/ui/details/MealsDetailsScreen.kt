package com.manoj.meals.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import com.manoj.meals.domain.dataModel.MealsCategory
import java.lang.Float.min

@Composable
fun MealsDetailsScreen(mealsCategory: MealsCategory?) {
    val scrollState = rememberScrollState()
    val offset = min(1f, 1 - (scrollState.value / 600f))

    val lazyListState = rememberLazyListState()
    val lazyListOffset = min(
        1f,
        1 - (lazyListState.firstVisibleItemScrollOffset / 600f + lazyListState.firstVisibleItemIndex)
    )

    val animateSize by animateDpAsState(targetValue = max(100.dp, 200.dp * offset), label = "")
    val animateLazySize by animateDpAsState(targetValue = max(100.dp, 200.dp * lazyListOffset), label = "")
    var mealProfilePictureValueState by remember {
        mutableStateOf(MealProfilePictureState.Normal)
    }
    val transition = updateTransition(targetState = mealProfilePictureValueState, label = "")
    val animateImageSize by transition.animateDp(targetValueByState = { it.size }, label = "")
    val animateColor by transition.animateColor(targetValueByState = { it.color }, label = "")
    val animateBorderWidth by transition.animateDp(
        targetValueByState = { it.borderWidth },
        label = ""
    )


    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            Surface(shadowElevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = animateBorderWidth,
                            color = animateColor
                        )
                    ) {
                        AsyncImage(
                            model = mealsCategory?.imageUrl,
                            contentDescription = "Meal Category Image",
                            modifier = Modifier
                                .size(animateLazySize)
                                .padding(8.dp)
                        )
                    }
                    Text(
                        text = mealsCategory?.category ?: "No such Category",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }


            }
            val dummyList = (0..100).map { it.toString() }
            LazyColumn(state = lazyListState) {
                items(dummyList) {
                    Text(text = "This is $it text element", modifier = Modifier.padding(16.dp))
                }
            }
            /*Button(modifier = Modifier.padding(16.dp), onClick = {
                mealProfilePictureValueState =
                    if (mealProfilePictureValueState == MealProfilePictureState.Normal)
                        MealProfilePictureState.Expanded else
                        MealProfilePictureState.Normal
            }) {
                Text(
                    text = "Click here to expand Meal Picture"
                )
            }*/
        }

    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Black, 80.dp, 8.dp),
    Expanded(Color.Green, 320.dp, 4.dp)
}