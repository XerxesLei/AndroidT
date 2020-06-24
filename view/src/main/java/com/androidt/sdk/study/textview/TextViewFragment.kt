package com.androidt.sdk.study.textview

import android.content.res.Resources
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.androidt.sdk.study.R
import kotlinx.android.synthetic.main.fragment_text_view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TextViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TextViewFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spannableString = SpannableString("editvieweditvieweditvieweditview")
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.colorAccent)), 0,2, 0)

        //ClickableSpan 点击事件要响应必须和LinkMovementMethod.getInstance()配合使用
        /**
        * If an object of this type is attached to the text of a TextView
        * with a movement method of LinkMovementMethod, the affected spans of
        * text can be selected. If selected and clicked, the {@link #onClick} method will
        * be called.
        * <p>
        * The text with a <code>ClickableSpan</code> attached will be underlined and the link color will be
        * used as a text color. The default link color is the theme's accent color or
        * <code>android:textColorLink</code> if this attribute is defined in the theme.
        * For example, considering that we have a <code>CustomClickableSpan</code> that extends
        * <code>ClickableSpan</code>, it can be used like this:
        * <pre>{@code SpannableString string = new SpannableString("Text with clickable text");
            *string.setSpan(new CustomClickableSpan(), 10, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}</pre>
        * <img src="{@docRoot}reference/android/images/text/style/clickablespan.png" />
        * <figcaption>Text with <code>ClickableSpan</code>.</figcaption>
        */
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "您点击了", Toast.LENGTH_LONG).show()
            }

        },2, 5, 0)
        text.movementMethod = LinkMovementMethod.getInstance()
        text.text = spannableString

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TextViewFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TextViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
