//вариант задания 1: 6 % 6 = 0
//вариант задания 2: 6 % 3 = 0
//вариант формата файла: 6 % 4 = 2 На вход JSON, На выход XML

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Main{
    private static void writeXml(Document doc, OutputStream output) throws TransformerException {//метод для вывода xml-документа

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");//задаёт перенос строк и ступенчатый вид в выходом документе

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

    public static void main(String[] args) {

        try {
            JsonObject Products = Json.createReader(new FileInputStream("Products.json")).readObject();
            JsonArray ListProducts = Products.getJsonArray("products");//чтение списка продуктов из файла Products.json

            JsonObject Sellers = Json.createReader(new FileInputStream("Sellers.json")).readObject();
            JsonArray ListSellers = Sellers.getJsonArray("sellers");//чтение списка продавцов из файла Sellers.json

            JsonObject SHP = Json.createReader(new FileInputStream("seller_has_product.json")).readObject();
            JsonArray ListSHP = SHP.getJsonArray("seller_has_product");//чтение списка товаров у продавцов из файла seller_has_product.json

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();//
            Element store = doc.createElement("store");//создание первого, основого элемента в xml-документе
            doc.appendChild(store);

            for (int i=0;i<ListProducts.size();i++) {//метод нахождения максимального количества товара среди продавцов и вывод "Имя Фамилия продавца - товар - количество товара"
                int max_q = 0;
                String product = ListProducts.getJsonObject(i).getString("id");
                String seller = "";
                for (int j=0;j<ListSHP.size();j++) {//нахождение максимального количества продукта, взятого из списка ListProducts
                    if (ListSHP.getJsonObject(j).getString("id_product").equals(product)) {
                        int quantity = ListSHP.getJsonObject(j).getInt("quantity");
                        if (max_q < quantity) {
                            max_q = quantity;
                            seller = ListSHP.getJsonObject(j).getString("id_seller");//запоминаем id продавца с наибольшим количеством товара
                        }
                    }
                }
                Element ElSeller = doc.createElement("seller");
                store.appendChild(ElSeller);
                for (int j=0;j< ListSellers.size();j++) {//находим продавца в списке продавцов по id в переменной seller
                    if (ListSellers.getJsonObject(j).getString("id").equals(seller)) {//если находим нужного продавца, создаём в xml-документе элементы FirstName и SecondName с соответствующим текстом
                        Element FirstName = doc.createElement("FirstName");
                        FirstName.setTextContent(ListSellers.getJsonObject(j).getString("First_name"));
                        ElSeller.appendChild(FirstName);

                        Element SecondName = doc.createElement("SecondName");
                        SecondName.setTextContent(ListSellers.getJsonObject(j).getString("Second_name"));
                        ElSeller.appendChild(SecondName);
                        break;
                    }
                }
                Element NameProduct = doc.createElement("NameProduct");
                NameProduct.setTextContent(ListProducts.getJsonObject(i).getString("name"));//добавляем элемент в xml-документе NameProduct с именем товара
                ElSeller.appendChild(NameProduct);

                Element QuantityProduct = doc.createElement("QuantityProduct");//добавляем элемент в xml-документе QuantityProduct с количеством товара
                QuantityProduct.setTextContent(String.format("%d",max_q));
                ElSeller.appendChild(QuantityProduct);
            }

            try (FileOutputStream output = new FileOutputStream("Quantity_Products.xml")) {
                writeXml(doc, output);//выводим документ - список товаров и продавцы с наибольшим количеством этого товара
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (TransformerException e) {
                e.printStackTrace();
            }

            JsonObject Sales = Json.createReader(new FileInputStream("Sales.json")).readObject();
            JsonArray ListSales = Sales.getJsonArray("sales");//чтение списка проданных товаров
            Map<String,Integer> Dates = new HashMap<String,Integer>();//создание map, где ключ - дата, а значение - количество совершёных продаж в этот день

            Document doc2 = docBuilder.newDocument();//создание документа xml
            Element ElShop = doc2.createElement("Shop");
            doc2.appendChild(ElShop);

            for (int i =0; i<ListSales.size();i++) {
                String Date = ListSales.getJsonObject(i).getString("date");
                Dates.putIfAbsent(Date,0);//в map заносится (Date,0), если ключа Date не существует
                if (Dates.get(Date) == 0) {//если значение равно 0, дата обнаружена впервые и продажи по ней ещё не посчитаны
                    Dates.put(Date,1);//задаём значение 1 для даты Date, чтобы повторно не просматривать её в будущем
                    int count_sale = 0;
                    for (int j=i;j<ListSales.size();j++) {//так как дата была обнаружена в первый раз на элементе i, нет смысла идти от начального элемента: там совпадений мы не найдём
                        if (ListSales.getJsonObject(j).getString("date").equals(Date)) {
                            count_sale += ListSales.getJsonObject(j).getInt("quantity_sales");//подсчёт количества продаж, если дата совпадает
                        }
                    }
                    Element ElSales = doc2.createElement("sales");
                    ElShop.appendChild(ElSales);

                    Element ElDate = doc2.createElement("Date");//добавляем элемент в xml-документ Date с датой
                    ElDate.setTextContent(Date);
                    ElSales.appendChild(ElDate);

                    Element ElCounts = doc2.createElement("QuantitySales");//добавляем элемент в xml-документ QuantitySales с количеством продаж
                    ElCounts.setTextContent(String.format("%d",count_sale));
                    ElSales.appendChild(ElCounts);
                }
            }

            try (FileOutputStream output2 = new FileOutputStream("Quantity_Sales.xml")) {
                writeXml(doc2, output2);//выводим документа - списка количества продаж по датам
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (TransformerException e) {
                e.printStackTrace();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            System.out.println("Кажется, во входных файлах пропущены данные");
        }
    }
}
