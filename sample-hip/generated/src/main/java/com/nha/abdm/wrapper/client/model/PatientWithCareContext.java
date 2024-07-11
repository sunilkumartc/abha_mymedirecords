/*
 * Swagger HIP Facade - OpenAPI 3.0
 * This is a set of interfaces based on the OpenAPI 3.0 specification for a wrapper client
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.nha.abdm.wrapper.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.nha.abdm.wrapper.client.model.CareContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.nha.abdm.wrapper.client.invoker.JSON;

/**
 * PatientWithCareContext
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-04-22T00:27:32.411905600+05:30[Asia/Calcutta]")
public class PatientWithCareContext {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;

  public static final String SERIALIZED_NAME_REFERENCE_NUMBER = "referenceNumber";
  @SerializedName(SERIALIZED_NAME_REFERENCE_NUMBER)
  private String referenceNumber;

  public static final String SERIALIZED_NAME_CARE_CONTEXTS = "careContexts";
  @SerializedName(SERIALIZED_NAME_CARE_CONTEXTS)
  private List<CareContext> careContexts;

  public PatientWithCareContext() {
  }

  public PatientWithCareContext id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * ABHA Address
   * @return id
  **/
  @javax.annotation.Nullable
  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public PatientWithCareContext referenceNumber(String referenceNumber) {
    
    this.referenceNumber = referenceNumber;
    return this;
  }

   /**
   * Get referenceNumber
   * @return referenceNumber
  **/
  @javax.annotation.Nullable
  public String getReferenceNumber() {
    return referenceNumber;
  }


  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }


  public PatientWithCareContext careContexts(List<CareContext> careContexts) {
    
    this.careContexts = careContexts;
    return this;
  }

  public PatientWithCareContext addCareContextsItem(CareContext careContextsItem) {
    if (this.careContexts == null) {
      this.careContexts = new ArrayList<>();
    }
    this.careContexts.add(careContextsItem);
    return this;
  }

   /**
   * Get careContexts
   * @return careContexts
  **/
  @javax.annotation.Nullable
  public List<CareContext> getCareContexts() {
    return careContexts;
  }


  public void setCareContexts(List<CareContext> careContexts) {
    this.careContexts = careContexts;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatientWithCareContext patientWithCareContext = (PatientWithCareContext) o;
    return Objects.equals(this.id, patientWithCareContext.id) &&
        Objects.equals(this.referenceNumber, patientWithCareContext.referenceNumber) &&
        Objects.equals(this.careContexts, patientWithCareContext.careContexts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, referenceNumber, careContexts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatientWithCareContext {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    referenceNumber: ").append(toIndentedString(referenceNumber)).append("\n");
    sb.append("    careContexts: ").append(toIndentedString(careContexts)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("id");
    openapiFields.add("referenceNumber");
    openapiFields.add("careContexts");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to PatientWithCareContext
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (!PatientWithCareContext.openapiRequiredFields.isEmpty()) { // has required fields but JSON object is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in PatientWithCareContext is not found in the empty JSON string", PatientWithCareContext.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!PatientWithCareContext.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `PatientWithCareContext` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      if ((jsonObj.get("id") != null && !jsonObj.get("id").isJsonNull()) && !jsonObj.get("id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("id").toString()));
      }
      if ((jsonObj.get("referenceNumber") != null && !jsonObj.get("referenceNumber").isJsonNull()) && !jsonObj.get("referenceNumber").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `referenceNumber` to be a primitive type in the JSON string but got `%s`", jsonObj.get("referenceNumber").toString()));
      }
      if (jsonObj.get("careContexts") != null && !jsonObj.get("careContexts").isJsonNull()) {
        JsonArray jsonArraycareContexts = jsonObj.getAsJsonArray("careContexts");
        if (jsonArraycareContexts != null) {
          // ensure the json data is an array
          if (!jsonObj.get("careContexts").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `careContexts` to be an array in the JSON string but got `%s`", jsonObj.get("careContexts").toString()));
          }

          // validate the optional field `careContexts` (array)
          for (int i = 0; i < jsonArraycareContexts.size(); i++) {
            CareContext.validateJsonObject(jsonArraycareContexts.get(i).getAsJsonObject());
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!PatientWithCareContext.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'PatientWithCareContext' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<PatientWithCareContext> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(PatientWithCareContext.class));

       return (TypeAdapter<T>) new TypeAdapter<PatientWithCareContext>() {
           @Override
           public void write(JsonWriter out, PatientWithCareContext value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public PatientWithCareContext read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of PatientWithCareContext given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of PatientWithCareContext
  * @throws IOException if the JSON string is invalid with respect to PatientWithCareContext
  */
  public static PatientWithCareContext fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, PatientWithCareContext.class);
  }

 /**
  * Convert an instance of PatientWithCareContext to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

