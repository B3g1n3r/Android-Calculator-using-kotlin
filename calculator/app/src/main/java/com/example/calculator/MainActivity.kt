package com.example.calculator

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {

 private val button0: Button by bind(R.id.button0)
 private val button1: Button by bind(R.id.button1)
 private val button2: Button by bind(R.id.button2)
 private val button3: Button by bind(R.id.button3)
 private val button4: Button by bind(R.id.button4)
 private val button5: Button by bind(R.id.button5)
 private val button6: Button by bind(R.id.button6)
 private val button7: Button by bind(R.id.button7)
 private val button8: Button by bind(R.id.button8)
 private val button9: Button by bind(R.id.button9)

 private val button_open_bracket: Button by bind(R.id.button_open_bracket)
 private val button_close_bracket: Button by bind(R.id.button_close_bracket)
 private val exponent: Button by bind(R.id.button_exponent)
 private val buttonLog: Button by bind(R.id.button_log)

 private val buttonPercentage: Button by bind(R.id.button_percentage)
 private val buttonRoot: Button by bind(R.id.button_sqroot)
 private val buttonSquare: Button by bind(R.id.button_square)
 private val buttonFraction: Button by bind(R.id.button_xinverse)
 private val buttonCE: Button by bind(R.id.button_clear_all)
 private val buttonC: Button by bind(R.id.button_clear)
 private val buttonBackspace: Button by bind(R.id.button_edit)
 private val buttonDivision: Button by bind(R.id.button_division)
 private val buttonMultiplication: Button by bind(R.id.button_multiplication)
 private val buttonSubtraction: Button by bind(R.id.button_minus)
 private val buttonAddition: Button by bind(R.id.button_plus)
 private val buttonEqual: Button by bind(R.id.button_equal)
 private val buttonPlusMinus: Button by bind(R.id.button_ChangeSymbol)
 private val buttonDot: Button by bind(R.id.button_dot)
 private val button_xinverse: Button by bind(R.id.button_xinverse)

 private val displayText: TextView by bind(R.id.display)
 private var isResultDisplayed = false
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_main)

  button0.setOnClickListener{
   handleNumberButtonPressed(button0)
  }
  button1.setOnClickListener{
   handleNumberButtonPressed(button1)
  }
  button2.setOnClickListener{
   handleNumberButtonPressed(button2)
  }
  button3.setOnClickListener{
   handleNumberButtonPressed(button3)
  }
  button4.setOnClickListener{
   handleNumberButtonPressed(button4)
  }
  button5.setOnClickListener{
   handleNumberButtonPressed(button5)
  }
  button6.setOnClickListener{
   handleNumberButtonPressed(button6)
  }
  button7.setOnClickListener{
   handleNumberButtonPressed(button7)
  }
  button8.setOnClickListener{
   handleNumberButtonPressed(button8)
  }
  button9.setOnClickListener{
   handleNumberButtonPressed(button9)
  }
  buttonBackspace.setOnClickListener{
   backSpace()
  }
  buttonAddition.setOnClickListener{
   displayText.append("+")
  }
  buttonSubtraction.setOnClickListener{
   displayText.append("-")
  }
  buttonDivision.setOnClickListener{
   displayText.append("/")
  }
  buttonMultiplication.setOnClickListener{
   displayText.append("*")
  }
  buttonDot.setOnClickListener{
   displayText.append(".")
  }
  buttonPercentage.setOnClickListener{
   displayText.append("%")
  }
  button_open_bracket.setOnClickListener{
   displayText.append("(")
  }
  button_close_bracket.setOnClickListener{
   displayText.append(")")
  }
  exponent.setOnClickListener{
   displayText.append("^")
  }
  buttonEqual.setOnClickListener{
   var result = evaluation(displayText.text.toString())
   displayText.text = result.toString()
   isResultDisplayed = true
  }
  buttonCE.setOnClickListener{
   displayText.text = ""
  }
  buttonPlusMinus.setOnClickListener{
   var expression: String = displayText.text.toString().replace("\\s","")
   if(expression==null || expression==" " || expression[0]=='-'){
    displayText.text= expression.replace("-","")
   }else{
    displayText.text ="-$expression".replace("\\s","")
   }
  }
  buttonSquare.setOnClickListener{
   var value = displayText.text.toString()
   var inputValue = value.toDouble()
   var result = inputValue * inputValue
   displayText.text = result.toString()
   isResultDisplayed = true
  }
  buttonRoot.setOnClickListener{
   var value = displayText.text.toString()
   var inputValue = value.toDouble()
   displayText.text = sqrt(inputValue).toString()
   isResultDisplayed = true
  }
  button_xinverse.setOnClickListener{
   var value = displayText.text.toString()
   var Double_value = value.toDouble()
   displayText.text = (1/Double_value).toString()
   isResultDisplayed = true
  }
 }

 private fun handleNumberButtonPressed(button: Button) {
  val buttonValue = button.text.toString()

  if(!isResultDisplayed || isLastCharacterSymbol(displayText)){
   displayText.append(buttonValue)

  }else {
   displayText.text = buttonValue
   isResultDisplayed = false
  }

 }
private fun isLastCharacterSymbol(textView: TextView): Boolean {
 val text = textView.text.toString().trim()
 if (text.isNotEmpty()) {
  val lastChar = text.substring(text.length - 1)
  return !lastChar.matches(Regex("[a-zA-Z0-9]")) // Check if the last character is not a letter or digit
 }
 return false
}

 private fun evaluation(expression: String): Double?{
  return try{
   var exp = ExpressionBuilder(expression).build()
   exp.evaluate()
  }catch (e: Exception){
   null
  }
 }

 private fun backSpace(){
 val text = displayText.text.toString()
 if(text.isNotEmpty()){
  val newText = text.substring(0, text.length-1)
  displayText.text = newText
 }
}
 private fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
  // Function will be called only by the main thread to improve performance.
  return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(idRes) }
 }
    }


private operator fun CharSequence.set(i: Int, value: String) {

}


