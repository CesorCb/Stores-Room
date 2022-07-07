package com.cesor.android.storesprueba1.editModule

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cesor.android.storesprueba1.mainModule.MainActivity
import com.cesor.android.storesprueba1.R
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import com.cesor.android.storesprueba1.databinding.FragmentEditStoreBinding
import com.cesor.android.storesprueba1.editModule.viewModel.EditStoreViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class EditStoreFragment : Fragment() {
    private lateinit var mBinding : FragmentEditStoreBinding
    private var mActivity : MainActivity? = null
    private var mStoreEntity : StoreEntity? = null
    private var mIsEditMode : Boolean = false

    //MVVM
    private lateinit var mEditStoreViewModel : EditStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEditStoreViewModel = ViewModelProvider(requireActivity())[EditStoreViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mBinding = FragmentEditStoreBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //MVVM
        setupViewModel()
        setupTextFields()

    }

    private fun setupViewModel() {
        mEditStoreViewModel.getStoreSelected().observe(viewLifecycleOwner){
            mStoreEntity = it
            if (it.id != 0L){
            mIsEditMode = true
                setUiStore(it)
            } else {
                mIsEditMode = false
            }
            setupActionBar()
        }

        mEditStoreViewModel.getResult().observe(viewLifecycleOwner){ result ->
            hideKeyboard()

            when(result){
                is Long ->{
                    mStoreEntity!!.id = result
                    mEditStoreViewModel.setStoreSelected(mStoreEntity!!)
                    Toast.makeText(mActivity, getString(R.string.edit_store_save_message_success), Toast.LENGTH_LONG).show()
                    mActivity?.onBackPressed()

                }
                is StoreEntity -> {
                    mEditStoreViewModel.setStoreSelected(mStoreEntity!!)
                    Snackbar.make(mBinding.root,getString(R.string.edit_store_update_message_success), Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupActionBar() {
        mActivity = activity as MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity?.supportActionBar?.title = if(mIsEditMode) getString(R.string.edit_store_title_edit)
                                            else getString(R.string.edit_store_title_add)

        setHasOptionsMenu(true)
    }

    private fun setupTextFields() {
        with(mBinding){
            etPhotoUrl.addTextChangedListener { validateFields(tilPhotoUrl) }
            etPhone.addTextChangedListener { validateFields(tilPhone) }
            etName.addTextChangedListener { validateFields(tilName)
                loadImage(it.toString().trim())}
        }
    }

    private fun loadImage(url: String){
        Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .into(mBinding.imgPhoto)
    }



    private fun setUiStore(storeEntity: StoreEntity) {
        with(mBinding){
            etName.setText(storeEntity.name)
            etPhone.setText(storeEntity.phone)
            etWebsiteUrl.setText(storeEntity.website)
            etPhotoUrl.setText(storeEntity.photoUrl)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                hideKeyboard()
                mActivity?.onBackPressed()
                true
            }
            R.id.action_save ->{
                hideKeyboard()
                if (mStoreEntity != null &&
                    validateFields(mBinding.tilPhotoUrl, mBinding.tilPhone, mBinding.tilName)){
                    with(mStoreEntity!!){
                        name = mBinding.etName.text.toString().trim()
                        phone = mBinding.etPhone.text.toString().trim()
                        website = mBinding.etWebsiteUrl.text.toString().trim()
                        photoUrl = mBinding.etPhotoUrl.text.toString().trim()
                    }

                    if (mIsEditMode) mEditStoreViewModel.updateStore(mStoreEntity!!)
                    else mEditStoreViewModel.saveStore(mStoreEntity!!)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun validateFields(vararg textFields : TextInputLayout) : Boolean{
        var isValid = true
        for (textField in textFields){
            if (textField.editText?.text.toString().trim().isEmpty()){
                textField.error = getString(R.string.helper_text_required)
                isValid = false
            } else {
                textField.error = null
            }
        }
        if (!isValid) Snackbar.make(mBinding.root, getString(R.string.edit_store_save_message_valid),
            Snackbar.LENGTH_SHORT ).show()

        return isValid
    }

    private fun hideKeyboard(){
        val imm = mActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken,0)
    }

    override fun onDestroy() {
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mActivity?.supportActionBar?.title = getString(R.string.app_name)
        mEditStoreViewModel.setResult(Any())
        setHasOptionsMenu(false)
        super.onDestroy()
    }
}