package yc.droid.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CustomView : LinearLayout {
  constructor(context: Context?) : super(context) {
    initView()
  }

  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
    initView()
    getAttrs(attrs)
  }

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    initView()
    getAttrs(attrs, defStyleAttr)
  }

  private val root: LinearLayout by lazy {
    findViewById(R.id.rootLayout)
  }

  private val imageView: ImageView by lazy {
    findViewById(R.id.iconImageView)
  }

  private val textView: TextView by lazy {
    findViewById(R.id.titleTextView)
  }

  private fun initView() {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.view_custom, this, false)
    addView(view)
  }

  private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int? = null) {
    val typedArray = if (defStyleAttr == null) {
      context.obtainStyledAttributes(attrs, R.styleable.CustomView)
    } else {
      context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0)
    }

    setTypedArray(typedArray)
  }

  private fun setTypedArray(typedArray: TypedArray) {
    // image 속성
    val imageResource = typedArray.getResourceId(R.styleable.CustomView_image, R.drawable.tree)
    imageView.setImageResource(imageResource)

    // title 속성
    val titleString = typedArray.getString(R.styleable.CustomView_title)
    textView.text = titleString

    // backgroundColor 속성
    val backgroundColorResource = typedArray.getColor(R.styleable.CustomView_backgroundColor, Color.WHITE)
    root.setBackgroundColor(backgroundColorResource)

    // active 속성
    val activeState = typedArray.getBoolean(R.styleable.CustomView_active, true)
    alpha = if (activeState) 1.0f else 0.2f
  }
}