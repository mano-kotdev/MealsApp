package com.manoj.meals.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.manoj.meals.data.dataSource.api.ApiDataWrapper
import com.manoj.meals.domain.dataModel.MealsCategory
import com.manoj.meals.domain.dataModel.MealsCategoryResponse

@Composable
fun MealsCategoryScreen(viewModel: MealsCategoryViewModel = hiltViewModel(), navigationCallback: (String)->Unit) {
    val mealsResponse by viewModel.mealsState.collectAsState()

    var showLoader by remember { mutableStateOf(true) }

    when (mealsResponse) {
        is ApiDataWrapper.Success -> {
            showLoader = false
            LazyColumn(contentPadding = PaddingValues(8.dp)) {
                items((mealsResponse as ApiDataWrapper.Success<MealsCategoryResponse>).value.categories) { mealCategory ->
                    MealCategory(mealsCategory = mealCategory, navigationCallback)
                }
            }
        }

        is ApiDataWrapper.Loading -> {
            showLoader = true
        }

        else -> {

        }
    }
    ShowLoader(shouldShow = showLoader)
}

@Composable
fun ShowLoader(shouldShow: Boolean) {
    if (shouldShow) {
        Text(text = "Loading...", textAlign = TextAlign.Center)
    }
}


@Composable
fun MealCategory(mealsCategory: MealsCategory, navigationCallback: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navigationCallback(mealsCategory.id)
            }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            AsyncImage(
                model = mealsCategory.imageUrl,
                contentDescription = "Meal category image",
                modifier = Modifier
                    .size(92.dp)
                    .padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {
                Text(text = mealsCategory.category, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                CompositionLocalProvider(
                    value = LocalContentColor provides LocalContentColor.current.copy(
                        alpha = 0.6f
                    )
                ) {
                    Text(
                        text = mealsCategory.description, style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis, maxLines = if (isExpanded) 10 else 4
                    )
                }
            }
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand data",
                modifier = Modifier
                    .padding(16.dp)
                    .align(if (isExpanded) Alignment.Bottom else Alignment.CenterVertically)
                    .clickable {
                        isExpanded = !isExpanded
                    }
            )
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun MealsCategoryScreenPreview() {
    MealsCategoryScreen(){

    }
}
