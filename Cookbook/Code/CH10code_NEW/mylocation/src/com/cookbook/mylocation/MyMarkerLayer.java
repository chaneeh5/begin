package com.cookbook.mylocation;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;


public class MyMarkerLayer extends ItemizedOverlay{

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	public MyMarkerLayer(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
		populate();
	}
	public void addOverlayItem(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}
	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}
	@Override
	protected boolean onTap(int index) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MyLocation.mContext);
		dialog.setTitle(mOverlays.get(index).getTitle());
		dialog.setMessage(mOverlays.get(index).getSnippet());

		dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.cancel();
			}
		});	  

		dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.cancel();
			}
		});	 
	  
		dialog.show();
		// TODO Auto-generated method stub
		return super.onTap(index);
	}
	
}
