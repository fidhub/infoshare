package infoshare.restapi.common.position;

import hashwork.app.conf.RestUtil;
import hashwork.domain.ui.position.PositionType;
import hashwork.restapi.common.CommonBaseURI;

import java.util.Set;

/**
 * Created by hashcode on 2015/12/10.
 */
public class PositionTypeAPI {
    public static PositionType save(PositionType positionType) {
        RestUtil.save(CommonBaseURI.PositionType.POST, positionType, PositionType.class);
        return positionType;
    }

    public static PositionType findById(String id) {
        return RestUtil.getById(CommonBaseURI.PositionType.GET_ID, id, PositionType.class);

    }

    public static Set<PositionType> findAll() {
        return RestUtil.getAll(CommonBaseURI.PositionType.GETALL, PositionType.class);
    }
}
