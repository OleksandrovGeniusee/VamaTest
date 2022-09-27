package com.example.vamatest.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.vamatest.R

@Composable
fun ErrorDialog(onDismiss: () -> Unit, retry: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss.invoke()
        },
        DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
                .background(
                    color = colorResource(id = R.color.background),
                    shape = RoundedCornerShape(8.dp)
                ).padding(dimensionResource(id = R.dimen.margin_item_space_default)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.bad_internet_connection_text),
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_item_space_default)),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.album_artist_name_list)
                )
            )
            Button(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_item_space_extra_big)),
                onClick = {
                    retry.invoke()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bright_blue))
            ) {
                Text(
                    text = stringResource(id = R.string.retry),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.album_name_list)
                    )
                )
            }
        }
    }
}