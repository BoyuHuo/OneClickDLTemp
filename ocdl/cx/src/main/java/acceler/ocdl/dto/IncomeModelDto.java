package acceler.ocdl.dto;

import acceler.ocdl.model.Model;

import java.io.Serializable;

public class IncomeModelDto {


    private Long modelId;
    private Long modelTypeId;
    private String status;
    private int bigVersion;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }


    public Long getModelTypeId() {
        return modelTypeId;
    }

    public void setModelTypeId(Long modelTypeId) {
        this.modelTypeId = modelTypeId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBigVersion() {
        return bigVersion;
    }

    public void setBigVersion(int bigVersion) {
        this.bigVersion = bigVersion;
    }

    public Model convert2Model() {
        Model model = new Model();

        model.setId(this.modelId);
        model.setModelTypeId(this.modelTypeId);
        model.setStatus(Model.Status.valueOf(this.status.toUpperCase()));

        return model;

    }
}
