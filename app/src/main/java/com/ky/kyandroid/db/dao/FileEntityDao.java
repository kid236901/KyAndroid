package com.ky.kyandroid.db.dao;

import android.util.Log;

import com.ky.kyandroid.db.BaseDao;
import com.ky.kyandroid.entity.FileEntity;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by Caizhui on 2017/6/11.
 * 文件Dao
 */

public class FileEntityDao extends BaseDao {
    /**
     * 标识
     */
    private static final String TAG = "FileEntityDao";


    @Override
    public boolean ifExist() {
        return false;
    }

    /**
     * 保存文件信息
     *
     * @param entity
     * @return
     */
    public boolean saveFileEntity(FileEntity entity) {
        boolean flag = false;
        try {
            saveOrUpdateEntity(entity);
            flag = true;
        } catch (DbException e) {
            Log.i(TAG, "信息保存异常>> " + e.getMessage());
        }
        return flag;
    }

    /**
     * 更新文件信息描述
     *
     * @param entity
     * @return
     */
    public boolean updateFileEntityByFileName(FileEntity entity,String params) {
        boolean flag = false;
        try {
            db.update(entity, String.valueOf(WhereBuilder.b("fileName","=", entity.getFileName())),params);
            flag = true;
        } catch (DbException e) {
            Log.i(TAG, "信息保存异常>> " + e.getMessage());
        }
        return flag;
    }

    /**
     * 查找列表
     *
     * @param
     * @return
     */
    public List<FileEntity> queryList(String sjId) {
        try {
            List<FileEntity> eventEntryList = db.selector(FileEntity.class).where("sjId", "==", sjId).findAll();
            if (eventEntryList != null && eventEntryList.size() > 0) {
                return eventEntryList;
            }
        } catch (DbException e) {
            Log.i(TAG, "信息查询异常-queryList >> " + e.getMessage());
        }
        return null;
    }

    /**
     * 查找列表
     *
     * @param
     * @return
     */
    public FileEntity queryFileEntity(int uuid) {
        try {
            List<FileEntity> eventEntryList = db.selector(FileEntity.class).where("uuid", "==", uuid).findAll();
            if (eventEntryList != null && eventEntryList.size() > 0) {
                return eventEntryList.get(0);
            }
        } catch (DbException e) {
            Log.i(TAG, "信息查询异常-queryList >> " + e.getMessage());
        }
        return null;
    }


    public boolean deleteEventEntry(int uuid) {
        boolean flag = false;
        try {
            db.delete(FileEntity.class, WhereBuilder.b("uuid", "=", uuid));
            flag = true;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteEventEntry(String fileName) {
        boolean flag = false;
        try {
            db.delete(FileEntity.class, WhereBuilder.b("fileName", "=", fileName));
            flag = true;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteEventEntryBySjId(String sjId) {
        boolean flag = false;
        try {
            db.delete(FileEntity.class, WhereBuilder.b("sjId", "=", sjId));
            flag = true;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    public boolean updateFileEntity(FileEntity entity) {
        boolean flag = false;
        try {
            db.update(entity, String.valueOf(WhereBuilder.b("uuid", "==", entity.getUuid())), "fileUrl", "fileMs", "fileName");
            flag = true;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
