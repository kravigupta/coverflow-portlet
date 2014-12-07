package me.rkg.plugins.util;

import java.util.Comparator;

import com.liferay.portal.kernel.repository.model.FileEntry;

public class FileEntryComparator implements Comparator<FileEntry> {

	public int compare(FileEntry o1, FileEntry o2) {
		if(o1.getFileEntryId() > o2.getFileEntryId()){
			return -1;
		}else{
			return 1;
		}
	}

}
