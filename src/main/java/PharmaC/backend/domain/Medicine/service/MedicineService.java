package PharmaC.backend.domain.Medicine.service;

import PharmaC.backend.domain.Medicine.dto.MedicineDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicineService {

    /**
     * 의약품 전체 조회
     * @return
     * @throws IOException
     */
    public List<MedicineDTO> getAllMedicine() throws IOException {

        List<MedicineDTO> medicineDTOS = new ArrayList<>();

        for(int i=1; i<=47; i++) {
            try {
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=CT6PLYXzJFrL%2B3YMNNnYsm0oK6giwUb%2Fh7vXt6UO0mQ1OGAfiaL1gn6U%2FK4DXNWnP1hlA3OW2AYa0Bevwhr9%2Fw%3D%3D"); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(i), "UTF-8")); /*페이지번호*/
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
                urlBuilder.append("&" + URLEncoder.encode("entpName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*업체명*/
                urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*제품명*/
                urlBuilder.append("&" + URLEncoder.encode("itemSeq", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품목기준코드*/
                urlBuilder.append("&" + URLEncoder.encode("efcyQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약의 효능은 무엇입니까?*/
                urlBuilder.append("&" + URLEncoder.encode("useMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떻게 사용합니까?*/
                urlBuilder.append("&" + URLEncoder.encode("atpnWarnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약을 사용하기 전에 반드시 알아야 할 내용은 무엇입니까?*/
                urlBuilder.append("&" + URLEncoder.encode("atpnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약의 사용상 주의사항은 무엇입니까?*/
                urlBuilder.append("&" + URLEncoder.encode("intrcQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약을 사용하는 동안 주의해야 할 약 또는 음식은 무엇입니까?*/
                urlBuilder.append("&" + URLEncoder.encode("seQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떤 이상반응이 나타날 수 있습니까?*/
                urlBuilder.append("&" + URLEncoder.encode("depositMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떻게 보관해야 합니까?*/
//        urlBuilder.append("&" + URLEncoder.encode("openDe","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*공개일자*/
//        urlBuilder.append("&" + URLEncoder.encode("updateDe","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*수정일자*/
                urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default:xml*/

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());

                BufferedReader rd;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }

                // rd.close();
                // conn.disconnect();
                //System.out.println(sb.toString());

                // Object 매핑
                // json 파싱
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<>();
                map = objectMapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {
                });

                Map<String, Object> body = (Map<String, Object>) map.get("body");
                List<Map<String, Object>> itemList = (List<Map<String, Object>>) body.get("items");

                if (itemList == null) {
                    // "itemList"이 null인 경우, 데이터가 없음을 나타내므로 처리를 중지하고 다음 반복으로 진행
                    continue;
                }

                for (Map<String, Object> item : itemList) {
                    MedicineDTO rs = MedicineDTO.builder()
                            .name(item.get("itemName").toString().describeConstable().orElse("null"))
                            .itemCode(item.computeIfAbsent("itemSeq",v -> v).toString())
                            //.itemCode(item.get("itemSeq").toString().describeConstable().orElse("null"))
                            .company(item.computeIfAbsent("entpName",v -> v).toString())
                            //.company(item.get("entpName").toString().describeConstable().orElse("null"))
                            .effect(item.computeIfAbsent("efcyQesitm",v -> v).toString())
                            //.effect(item.get("efcyQesitm").toString().describeConstable().orElse("null"))
                            .takeMethod(item.computeIfAbsent("useMethodQesitm",v -> v).toString())
                            //.takeMethod(item.get("useMethodQesitm").toString().describeConstable().orElse("null"))
                            .precaution(item.computeIfAbsent("atpnWarnQesitm",v -> v).toString())
                            //.precaution(item.get("atpnWarnQesitm").toString().describeConstable().orElse("null"))
                            .caution(item.computeIfAbsent("atpnQesitm",v -> v).toString())
                            //.caution(item.get("atpnQesitm").toString().describeConstable().orElse("null"))
                            .interaction((item.computeIfAbsent("intrcQesitm", v -> v).toString()))
                            //.interaction(item.get("intrcQesitm").toString().describeConstable().orElse("null"))
                            .sideEffect(item.computeIfAbsent("seQesitm",v -> v).toString())
                            //.sideEffect(item.get("seQesitm").toString().describeConstable().orElse("null"))
                            .storage(item.computeIfAbsent("depositMethodQesitm",v -> v).toString()).build();
                            //.storage(item.get("depositMethodQesitm").toString().describeConstable().orElse("null")).build();

                    medicineDTOS.add(rs);
                    // System.out.println("test1: "+rs.getMain_title());
                }
                rd.close();
                conn.disconnect();

            } catch (UnsupportedEncodingException | MalformedURLException | ProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } /*Service Key*/ catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            }
        }
        return medicineDTOS;
    }

    public List<MedicineDTO> getSelectedMedicine(Long code) throws IOException {

        List<MedicineDTO> medicineDTOS = new ArrayList<>();

        for(int i=1; i<=47; i++) {
            try {
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=CT6PLYXzJFrL%2B3YMNNnYsm0oK6giwUb%2Fh7vXt6UO0mQ1OGAfiaL1gn6U%2FK4DXNWnP1hlA3OW2AYa0Bevwhr9%2Fw%3D%3D"); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(i), "UTF-8")); /*페이지번호*/
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
                urlBuilder.append("&" + URLEncoder.encode("entpName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*업체명*/
                urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*제품명*/
                urlBuilder.append("&" + URLEncoder.encode("itemSeq", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(code), "UTF-8")); /*품목기준코드*/
                urlBuilder.append("&" + URLEncoder.encode("efcyQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약의 효능은 무엇입니까?*/
                urlBuilder.append("&" + URLEncoder.encode("useMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떻게 사용합니까?*/
                urlBuilder.append("&" + URLEncoder.encode("atpnWarnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약을 사용하기 전에 반드시 알아야 할 내용은 무엇입니까?*/
                urlBuilder.append("&" + URLEncoder.encode("atpnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약의 사용상 주의사항은 무엇입니까?*/
                urlBuilder.append("&" + URLEncoder.encode("intrcQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약을 사용하는 동안 주의해야 할 약 또는 음식은 무엇입니까?*/
                urlBuilder.append("&" + URLEncoder.encode("seQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떤 이상반응이 나타날 수 있습니까?*/
                urlBuilder.append("&" + URLEncoder.encode("depositMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*이 약은 어떻게 보관해야 합니까?*/
//        urlBuilder.append("&" + URLEncoder.encode("openDe","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*공개일자*/
//        urlBuilder.append("&" + URLEncoder.encode("updateDe","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*수정일자*/
                urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default:xml*/

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode());

                BufferedReader rd;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }

                // rd.close();
                // conn.disconnect();
                //System.out.println(sb.toString());

                // Object 매핑
                // json 파싱
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<>();
                map = objectMapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {
                });

                Map<String, Object> body = (Map<String, Object>) map.get("body");
                List<Map<String, Object>> itemList = (List<Map<String, Object>>) body.get("items");

                if (itemList == null) {
                    // "itemList"이 null인 경우, 데이터가 없음을 나타내므로 처리를 중지하고 다음 반복으로 진행
                    continue;
                }

                for (Map<String, Object> item : itemList) {
                    MedicineDTO rs = MedicineDTO.builder()
                            .name(item.get("itemName").toString().describeConstable().orElse("null"))
                            .itemCode(item.computeIfAbsent("itemSeq",v -> v).toString())
                            //.itemCode(item.get("itemSeq").toString().describeConstable().orElse("null"))
                            .company(item.computeIfAbsent("entpName",v -> v).toString())
                            //.company(item.get("entpName").toString().describeConstable().orElse("null"))
                            .effect(item.computeIfAbsent("efcyQesitm",v -> v).toString())
                            //.effect(item.get("efcyQesitm").toString().describeConstable().orElse("null"))
                            .takeMethod(item.computeIfAbsent("useMethodQesitm",v -> v).toString())
                            //.takeMethod(item.get("useMethodQesitm").toString().describeConstable().orElse("null"))
                            .precaution(item.computeIfAbsent("atpnWarnQesitm",v -> v).toString())
                            //.precaution(item.get("atpnWarnQesitm").toString().describeConstable().orElse("null"))
                            .caution(item.computeIfAbsent("atpnQesitm",v -> v).toString())
                            //.caution(item.get("atpnQesitm").toString().describeConstable().orElse("null"))
                            .interaction((item.computeIfAbsent("intrcQesitm", v -> v).toString()))
                            //.interaction(item.get("intrcQesitm").toString().describeConstable().orElse("null"))
                            .sideEffect(item.computeIfAbsent("seQesitm",v -> v).toString())
                            //.sideEffect(item.get("seQesitm").toString().describeConstable().orElse("null"))
                            .storage(item.computeIfAbsent("depositMethodQesitm",v -> v).toString()).build();
                    //.storage(item.get("depositMethodQesitm").toString().describeConstable().orElse("null")).build();

                    medicineDTOS.add(rs);
                    // System.out.println("test1: "+rs.getMain_title());
                }
                rd.close();
                conn.disconnect();

            } catch (UnsupportedEncodingException | MalformedURLException | ProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } /*Service Key*/ catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            }
        }
        return medicineDTOS;
    }

}
