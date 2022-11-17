
package com.app.recipe;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;


public class MyProgressDialog extends Dialog {



	public static MyProgressDialog show(Context context, CharSequence title, CharSequence message,
                                        boolean indeterminate, boolean cancelable, OnCancelListener cancelListener) {
		MyProgressDialog dialog = new MyProgressDialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.progressbar_common);
		dialog.setCancelable(false);

		dialog.setOnCancelListener(cancelListener);
		/* The next line will add the ProgressBar to the dialog. */
		dialog.show();

		return dialog;
	}

	public MyProgressDialog(Context context) {
		super(context, android.R.style.Theme_Translucent);
	}
}
