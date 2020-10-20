import java.util.ArrayList;
import com.google.gson.internal.LinkedTreeMap;

public class TestJson {

    private final LinkedTreeMap metadata;
    private final Double apiVersion;
    private final String apiKey;
    private final ArrayList entrypoints;

    public TestJson(LinkedTreeMap metadata, Double apiVersion, String apiKey, ArrayList entrypoints) {
        this.metadata = metadata;
        this.apiVersion = apiVersion;
        this.apiKey = apiKey;
        this.entrypoints = entrypoints;
    }

    public LinkedTreeMap getMetadata() {
        return metadata;
    }

    public Double getApiVersion() {
        return apiVersion;
    }

    public String getApiKey() {
        return apiKey;
    }

    public ArrayList getEntrypoints() {
        return entrypoints;
    }

}
