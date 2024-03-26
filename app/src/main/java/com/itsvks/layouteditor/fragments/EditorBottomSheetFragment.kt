package com.itsvks.layouteditor.fragments

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itsvks.layouteditor.R
import com.itsvks.layouteditor.databinding.FragmentEditorBottomSheetItemBinding
import com.itsvks.layouteditor.databinding.FragmentEditorBottomSheetBinding

// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    EditorBottomSheetFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class EditorBottomSheetFragment : BottomSheetDialogFragment() {

  private var _binding: FragmentEditorBottomSheetBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentEditorBottomSheetBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    list.layoutManager = GridLayoutManager(context, 2)
    activity?.findViewById<RecyclerView>(R.id.list)?.adapter =
      arguments?.getInt(ARG_ITEM_COUNT)?.let { ItemAdapter(it) }
  }

  private inner class ViewHolder internal constructor(binding: FragmentEditorBottomSheetItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    internal val text: TextView = binding.text
  }

  private inner class itemAdapter internal constructor(private val mItemCount: Int) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

      return ViewHolder(
        FragmentEditorBottomSheetItemBinding.inflate(
          LayoutInflater.from(parent.context),
          parent,
          false
        )
      )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.text.text = position.toString()
    }

    override fun getItemCount(): Int {
      return mItemCount
    }
  }

  companion object {

    // TODO: Customize parameters
    fun newInstance(itemCount: Int): EditorBottomSheetFragment =
      EditorBottomSheetFragment().apply {
        arguments = Bundle().apply {
          putInt(ARG_ITEM_COUNT, itemCount)
        }
      }

  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}