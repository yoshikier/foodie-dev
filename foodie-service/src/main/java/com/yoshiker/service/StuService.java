package com.yoshiker.service;

import com.yoshiker.pojo.Stu;

public interface StuService {
    public Stu getStuInfo(int id);
    public void saveStu();
    public void updateStu(int id);
    public void deleteStu(int id);
}
