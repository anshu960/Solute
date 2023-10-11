package com.solute.ui.onboarding.appStory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier,
            state = rememberPagerState { pageCount },
            pageSpacing = 0.dp,
            horizontalAlignment = horizontalAlignment,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = state),
            key = null,
            pageNestedScrollConnection = remember(state) {
                PagerDefaults.pageNestedScrollConnection(state, Orientation.Horizontal)
            },
            pageContent = fun PagerScope.(it: Int) {

            }
        )
    }
}