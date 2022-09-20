package cu.gob.ith.domain.mappers;

import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.domain.model.login.LoginBody;

public class TransformLoginBodyToApiLoginBody {

    public static ApiLoginBody map(LoginBody param) {
        return new ApiLoginBody(param.getUsername(), param.getCi(), param.getPassword());
    }

}
