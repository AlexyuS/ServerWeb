package com.app.utils;
import java.util.ArrayList;

public class HeaderMessage extends ArrayList<String> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(java.lang.String e) {
			if (e == null) {
				return false;
			} else {
				return super.add(e);
			}
		}
	}