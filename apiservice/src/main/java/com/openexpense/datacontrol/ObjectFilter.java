package com.openexpense.datacontrol;

import java.util.List;

/**
 * Created by jinyi on 16/7/15.
 */
public abstract class ObjectFilter {
    public abstract String toString(EntityUtil entityUtil,List<ObjectField> list,Boolean addRlation) throws Exception;
    public abstract String toString(EntityFieldUtil entityFieldUtil,List<ObjectField> list,Boolean addRlation) throws Exception;
    public abstract List<ObjectField> getFieldList();
}
