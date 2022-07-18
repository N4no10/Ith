package cu.gob.ith.domain.mappers;

import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.data.api.model.ApiApkVersion;
import cu.gob.ith.domain.model.ApkVersion;

public class TransformApiApkVersionToApkVersion {


    public static ApkVersion map(ApiApkVersion param) {
        return new ApkVersion(param.getId(),
                param.getVersion(),
                param.getUrl());
    }

    public static List<ApkVersion> mapList(List<ApiApkVersion> param) {
        List<ApkVersion> apkVersionList = new ArrayList<>();
        for (ApiApkVersion apiApkVersion : param
        ) {
            apkVersionList.add(map(apiApkVersion));
        }
        return apkVersionList;
    }
}
