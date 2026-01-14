package com.example.hope.components

import android.util.Log
import android.widget.CheckBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hope.R
import com.example.hope.data.room.PasswordProcessing
import com.example.hope.data.room.PasswordProperties
import com.example.hope.data.room.event.SongsEvent
import com.example.hope.data.room.state.SongsState
import com.example.hope.ui.theme.colorDark
import com.example.hope.ui.theme.colorDarkGray
import com.example.hope.ui.theme.colorLight
import com.example.hope.ui.theme.colorLightGray
import com.example.hope.ui.theme.colorRed
import com.example.hope.ui.theme.iconExit
import com.example.hope.ui.theme.iconHome
import com.example.hope.ui.theme.iconName
import com.example.hope.ui.theme.iconPassword
import com.example.hope.ui.theme.iconQuestion
import com.example.hope.ui.theme.iconSearch
import com.example.hope.ui.theme.iconSort
import com.example.hope.ui.theme.iconVisible
import com.example.hope.ui.theme.iconVisibleOff

@Composable
fun TinyText(value: String, isVisible: Boolean){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (isVisible) 1f else 0f)
            .heightIn(min = 40.dp), //minimum height
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorRed,
        textAlign = TextAlign.Left,
    )
}

@Composable
fun NormalText(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp), //minimum height
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
    , color = colorDark,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadText(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        )
        , color = colorDark,
        textAlign = TextAlign.Center
    )
}

@Composable
fun InputTextField(labelValue: String, iconValue: ImageVector, eventOnValueChange: (String) -> Unit){
    //remember the last value entered by the user
    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = {Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorDarkGray,
            focusedLabelColor = colorDarkGray,
            cursorColor = colorDarkGray
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = textValue.value,
        singleLine = true,
        maxLines = 1,
        onValueChange = {//when changed remembers the new value
            textValue.value = it
            eventOnValueChange(it)
        },
        leadingIcon = {
            //adding image
            Icon(imageVector = iconValue,
                contentDescription = "")
        }
    )
}

@Composable
fun PasswordTextField(labelValue: String, iconValue: ImageVector){
    val localFocusManager = LocalFocusManager.current
    //remember the last value entered by the user
    val textValue = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = {Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorDarkGray,
                focusedLabelColor = colorDarkGray,
                cursorColor = colorDarkGray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done),
            singleLine = true,
            maxLines = 1,
            value = textValue.value,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            onValueChange = {
                textValue.value = it
            },
            leadingIcon = {
                //adding image
                Icon(imageVector = iconValue,
                    contentDescription = "")
            },
            trailingIcon ={
                val iconImage = if(passwordVisible.value){
                    iconVisible
                }else{
                    iconVisibleOff
                }

                val description = if(passwordVisible.value){
                    stringResource(id = R.string.hide_password)
                }else{
                    stringResource(id = R.string.show_password)
                }

                IconButton(onClick = {
                    passwordVisible.value = !passwordVisible.value
                }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            },
            visualTransformation = if(passwordVisible.value){
                VisualTransformation.None
            } else{
                PasswordVisualTransformation()
            }
        )

    }

}

@Composable
fun InputPasswordTextField(labelValue: String, iconValue: ImageVector, eventOnValueChange: (String) -> Unit){
    val localFocusManager = LocalFocusManager.current
    //remember the last value entered by the user
    val textValue = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember { //password visibility property
        mutableStateOf(false)
    }
    val passwordValid = remember { //password visibility property
        mutableStateOf(false)
    }
    val passwordValidationFeedback = remember {
        mutableStateOf("")
    }
    var qqqq = "q"
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = {Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorDarkGray,
                focusedLabelColor = colorDarkGray,
                cursorColor = colorDarkGray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done),
            singleLine = true,
            maxLines = 1,
            value = textValue.value,
            keyboardActions = KeyboardActions {//action when the input is done
                localFocusManager.clearFocus()
            },
            onValueChange = {
                qqqq+="5"
                textValue.value = it //change the remembered value
                qqqq+="6"
                val passwordProcessing = PasswordProcessing(PasswordProperties()) //create the password processing object
                qqqq +="1"
                passwordValid.value = passwordProcessing.isValid(it) //check if the input string is a valid password
                if (passwordValid.value){//store the hash if valid
                    val hash = passwordProcessing.hashPassword(it)
                    eventOnValueChange(hash)
                    qqqq +="3"
                }else{//generate feedback if not valid
                    passwordValidationFeedback.value = passwordProcessing.generateFeedback()
                    qqqq+="2"
                }
                qqqq+=passwordValidationFeedback.value
            },
            leadingIcon = {
                //adding image
                Icon(imageVector = iconValue,
                    contentDescription = "")
            },
            trailingIcon ={
                val iconImage = if(passwordVisible.value){
                    iconVisible
                }else{
                    iconVisibleOff
                }

                val description = if(passwordVisible.value){
                    stringResource(id = R.string.hide_password)
                }else{
                    stringResource(id = R.string.show_password)
                }

                IconButton(onClick = {
                    passwordVisible.value = !passwordVisible.value
                }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            },
            visualTransformation = if(passwordVisible.value){
                VisualTransformation.None
            } else{
                PasswordVisualTransformation()
            }
        )
        TinyText(value = qqqq, isVisible = !passwordValid.value)
    }

}

@Composable
fun CheckboxComponent(value: String, clickableValue: String, onTextSelected: (String) -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp),
        //.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        ){
        val checkedState = remember{
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value, onCheckedChange = {
            checkedState.value = !checkedState.value
        })
        ClickableTextComponent(value = value, clickableValue = clickableValue, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, clickableValue: String, onTextSelected: (String) -> Unit){
    val annotatedString = buildAnnotatedString{
        append(value)
        withStyle(style = SpanStyle(color = colorDark)){
            pushStringAnnotation(tag = clickableValue, annotation = clickableValue)
            append(clickableValue)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        //check which part of the string is clicked
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also {span ->
                Log.d("ClickableTextComponent", "{$span}")
                //adding the callback
                onTextSelected(span.item)
            }
    })
}

@Composable
fun ButtonComponent(value: String, eventOnClick: () -> Unit){
    Button(onClick = { eventOnClick() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(colorRed)) {
        Text(text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorLight)
    }
}

@Composable
fun SquareButton(iconValue: ImageVector, eventOnClick: () -> Unit){
    Button(onClick = { eventOnClick() },
        modifier = Modifier
            .requiredSize(32.dp),
        shape = RoundedCornerShape(1.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(colorDark)) {
        Icon(imageVector = iconValue, contentDescription = "")
    }
}

@Composable
fun LightSquareButton(iconValue: ImageVector, eventOnClick: () -> Unit){
    Button(onClick = { eventOnClick() },
        modifier = Modifier
            .requiredSize(32.dp),
        shape = RoundedCornerShape(1.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(colorLight)) {
        Icon(imageVector = iconValue, contentDescription = "", tint = colorDark)
    }
}

@Composable
fun RectButton(value: String, eventOnClick: () -> Unit){
    Button(onClick = { eventOnClick() },
        modifier = Modifier
            .requiredHeight(32.dp)
            .widthIn(64.dp),
        shape = RoundedCornerShape(1.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(colorDark)) {
        Text(text = value,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            color = colorLight,
            textAlign = TextAlign.Center,)
    }
}

@Composable
fun Header(homeOnClick: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SquareButton(iconValue = iconHome) {
            homeOnClick()
        }
        SquareButton(iconValue = iconName) {
            homeOnClick()
        }
        SquareButton(iconValue = iconQuestion) {
            homeOnClick()
        }
        SquareButton(iconValue = iconQuestion) {
            homeOnClick()
        }
        SquareButton(iconValue = iconQuestion) {
            homeOnClick()
        }
        SquareButton(iconValue = iconExit) {
            homeOnClick()
        }
    }
}

@Composable
fun Navigation(searchOnClick: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        RectButton(value = "saved") {

        }
        RectButton(value = "search") {

        }
        RectButton(value = "create") {

        }
    }
}

@Composable
fun SearchField(onChange: (String) -> Unit, onSubmit: () -> Unit, onSort: () -> Unit){
    val textValue = remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(32.dp)
            .padding(4.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = "Search...",
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            onValueChange = {
                textValue.value = it
                onChange(it)
            })
        LightSquareButton(iconValue = iconSearch) {

        }
        LightSquareButton(iconValue = iconSort) {

        }
    }
}

@Composable
fun SongItem(
    state: SongsState,
    index: Int,
    onEvent: (SongsEvent) -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(colorDark)
            .padding(12.dp)
    ){
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = state.songs[index].description,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorLight
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.songs[index].description,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

//@Preview
@Composable
fun DefaultPreviewSignUp(){

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorLight)
            .padding(28.dp)
    ) {
        SearchField(onChange = {}, {}) {
            
        }
    }
}