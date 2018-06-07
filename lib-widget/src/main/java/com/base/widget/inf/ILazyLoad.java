package com.base.widget.inf;



/**
  * 类名: ILazyLoad
  * 描述 添加loadData
  * 作者 Comsys-linbinghuang
  * 时间 2014-9-1 下午2:17:58
  *
  */
public interface ILazyLoad {

	/**
	 * 加载数据
	 *
	 * @return
	 */
	 boolean firstdata();


	/** 可以传入页数
	 * 属性 page
	 */
	 void loadData(int page);
}
