package com.kolarbear.wanandroid.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kolarbear.wanandroid.bean.knowledge.ChildConverter;
import java.util.List;

import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "KNOWLEDGE_BEAN".
*/
public class KnowledgeBeanDao extends AbstractDao<KnowledgeBean, Long> {

    public static final String TABLENAME = "KNOWLEDGE_BEAN";

    /**
     * Properties of entity KnowledgeBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Index = new Property(0, Long.class, "index", true, "_id");
        public final static Property CourseId = new Property(1, int.class, "courseId", false, "COURSE_ID");
        public final static Property Id = new Property(2, int.class, "id", false, "ID");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Order = new Property(4, int.class, "order", false, "ORDER");
        public final static Property ParentChapterId = new Property(5, int.class, "parentChapterId", false, "PARENT_CHAPTER_ID");
        public final static Property Visible = new Property(6, int.class, "visible", false, "VISIBLE");
        public final static Property Select = new Property(7, boolean.class, "select", false, "SELECT");
        public final static Property Children = new Property(8, String.class, "children", false, "CHILDREN");
    }

    private final ChildConverter childrenConverter = new ChildConverter();

    public KnowledgeBeanDao(DaoConfig config) {
        super(config);
    }
    
    public KnowledgeBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"KNOWLEDGE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: index
                "\"COURSE_ID\" INTEGER NOT NULL ," + // 1: courseId
                "\"ID\" INTEGER NOT NULL ," + // 2: id
                "\"NAME\" TEXT," + // 3: name
                "\"ORDER\" INTEGER NOT NULL ," + // 4: order
                "\"PARENT_CHAPTER_ID\" INTEGER NOT NULL ," + // 5: parentChapterId
                "\"VISIBLE\" INTEGER NOT NULL ," + // 6: visible
                "\"SELECT\" INTEGER NOT NULL ," + // 7: select
                "\"CHILDREN\" TEXT);"); // 8: children
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_KNOWLEDGE_BEAN_NAME_DESC ON \"KNOWLEDGE_BEAN\"" +
                " (\"NAME\" DESC);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_KNOWLEDGE_BEAN_COURSE_ID_DESC ON \"KNOWLEDGE_BEAN\"" +
                " (\"COURSE_ID\" DESC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"KNOWLEDGE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, KnowledgeBean entity) {
        stmt.clearBindings();
 
        Long index = entity.getIndex();
        if (index != null) {
            stmt.bindLong(1, index);
        }
        stmt.bindLong(2, entity.getCourseId());
        stmt.bindLong(3, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
        stmt.bindLong(5, entity.getOrder());
        stmt.bindLong(6, entity.getParentChapterId());
        stmt.bindLong(7, entity.getVisible());
        stmt.bindLong(8, entity.getSelect() ? 1L: 0L);
 
        List children = entity.getChildren();
        if (children != null) {
            stmt.bindString(9, childrenConverter.convertToDatabaseValue(children));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, KnowledgeBean entity) {
        stmt.clearBindings();
 
        Long index = entity.getIndex();
        if (index != null) {
            stmt.bindLong(1, index);
        }
        stmt.bindLong(2, entity.getCourseId());
        stmt.bindLong(3, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
        stmt.bindLong(5, entity.getOrder());
        stmt.bindLong(6, entity.getParentChapterId());
        stmt.bindLong(7, entity.getVisible());
        stmt.bindLong(8, entity.getSelect() ? 1L: 0L);
 
        List children = entity.getChildren();
        if (children != null) {
            stmt.bindString(9, childrenConverter.convertToDatabaseValue(children));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public KnowledgeBean readEntity(Cursor cursor, int offset) {
        KnowledgeBean entity = new KnowledgeBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // index
            cursor.getInt(offset + 1), // courseId
            cursor.getInt(offset + 2), // id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.getInt(offset + 4), // order
            cursor.getInt(offset + 5), // parentChapterId
            cursor.getInt(offset + 6), // visible
            cursor.getShort(offset + 7) != 0, // select
            cursor.isNull(offset + 8) ? null : childrenConverter.convertToEntityProperty(cursor.getString(offset + 8)) // children
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, KnowledgeBean entity, int offset) {
        entity.setIndex(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCourseId(cursor.getInt(offset + 1));
        entity.setId(cursor.getInt(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setOrder(cursor.getInt(offset + 4));
        entity.setParentChapterId(cursor.getInt(offset + 5));
        entity.setVisible(cursor.getInt(offset + 6));
        entity.setSelect(cursor.getShort(offset + 7) != 0);
        entity.setChildren(cursor.isNull(offset + 8) ? null : childrenConverter.convertToEntityProperty(cursor.getString(offset + 8)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(KnowledgeBean entity, long rowId) {
        entity.setIndex(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(KnowledgeBean entity) {
        if(entity != null) {
            return entity.getIndex();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(KnowledgeBean entity) {
        return entity.getIndex() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}