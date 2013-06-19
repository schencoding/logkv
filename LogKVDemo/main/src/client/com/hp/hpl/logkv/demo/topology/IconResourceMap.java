package com.hp.hpl.logkv.demo.topology;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.hp.hpl.logkv.demo.model.LogSource;
import com.hp.hpl.logkv.demo.model.WorkerNode;

import edu.uci.ics.jung.visualization.LayeredIcon;

/**
 * Icon resource map.
 * @author Edmond
 */
public final class IconResourceMap {

	/**
	 * prevent to instance.
	 */
	private IconResourceMap() {
	}

	/**
	 * icon map.
	 */
	public static final Map<String, Icon> ICON_MAP;

	static {

		ICON_MAP = new HashMap<String, Icon>();

		ICON_MAP.put(WorkerNode.class.getSimpleName(),
				new LayeredIcon(new ImageIcon(IconResourceMap.class.getResource(WorkerNode.ICON_PATH)).getImage()));

		ICON_MAP.put(LogSource.class.getSimpleName(),
				new LayeredIcon(new ImageIcon(IconResourceMap.class.getResource(LogSource.ICON_PATH)).getImage()));

	}

}
