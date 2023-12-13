package com.example.common.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.common.R


@Composable
fun ButtonWithIcon(
    @DrawableRes icon: Int,
    text:String,
    onClick: () -> Unit ={}
) {
    Button(onClick = onClick) {
        Image(
            painterResource(id = icon),
            modifier = Modifier.size(20.dp),
            contentDescription ="Cart button icon",
            )

        Text(text = text,Modifier.padding(start = 10.dp))
    }
}




//Rectangle Shape:

@Composable
fun ButtonWithRectangleShape(
    text:String,
    onClick: () -> Unit ={}
) {
    Button(onClick = onClick, shape = RectangleShape) {
        Text(text = text)
    }
}
//Round Corner Shape:

@Composable
fun ButtonWithRoundCornerShape() {
    Button(onClick = {}, shape = RoundedCornerShape(20.dp)) {
        Text(text = "Round corner shape")
    }
}



@Composable
fun ButtonWithCutCornerShape() {
    //CutCornerShape(percent: Int)- it will consider as percentage
    //CutCornerShape(size: Dp)- you can pass Dp also.
    //Here we use Int, so it will take percentage.
    Button(onClick = {}, shape = CutCornerShape(10)) {
        Text(text = "Cut corner shape")
    }
}




@Composable
fun ButtonWithBorder(
    text: String
) {
    Button(
        onClick = {
            //your onclick code
        },
        border = BorderStroke(1.dp, Color.Red),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
    ) {
        Text(text = text , color = Color.DarkGray)
    }
}






