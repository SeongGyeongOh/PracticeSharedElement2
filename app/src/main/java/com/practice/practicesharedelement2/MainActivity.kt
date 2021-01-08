package com.practice.practicesharedelement2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.practice.practicesharedelement2.ui.PracticeSharedElement2Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeSharedElement2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RootScreen()
                }
            }
        }
    }
}

@Composable
fun RootScreen() {
    Column(Modifier.fillMaxWidth().fillMaxHeight()) {
        ListScreen()
    }
}

@Composable
fun ListScreen() {
    WithConstraints() {
        val maxWidth = with(AmbientDensity.current) {constraints.maxWidth.toDp()}
        val maxHeight = with(AmbientDensity.current) {constraints.maxHeight.toDp()}
        Column() {
            val isExpand = remember { mutableStateOf(false)}
            LazyColumn() {
                itemsIndexed(dummyList) { index, item ->
                    ListItem(index = index, item= item, isExpand, maxWidth, maxHeight)
                }
            }
        }
    }
}

@Composable
fun ListItem(index: Int, item: Dummy, isExpand: MutableState<Boolean>, maxWidth: Dp, maxHeight: Dp) {
//    WithConstraints {

        Box() {
            Column(
                    if(isExpand.value) {
                        Modifier.preferredWidth(maxWidth)
                                .preferredHeight(maxHeight)
                                .clickable(onClick = {isExpand.value = !isExpand.value})
                    } else {
                        Modifier.preferredWidth(300.dp)
                                .preferredHeight(300.dp)
                                .clickable(onClick = {isExpand.value = !isExpand.value})
                    }

            ) {
                Image(
                        imageResource(id = item.img),
                        Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillBounds
                )
                Text(item.content)
            }
        }
//    }
}

data class Dummy(val img: Int, val content: String)

val dummyList = listOf<Dummy>(
    Dummy(R.drawable.event1, "event1"),
    Dummy(R.drawable.event2, "event2"),
    Dummy(R.drawable.event3, "event3"),
    Dummy(R.drawable.event4, "event4"),
    Dummy(R.drawable.event5, "event5")
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PracticeSharedElement2Theme {
        RootScreen()
    }
}