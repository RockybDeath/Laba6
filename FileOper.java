import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class FileOper {
    private static String Output;
    public static ConcurrentSkipListMap<String, Human> fromXml(String xml) {
//        TreeMap<String, Human> HumansTree = new TreeMap<String, Human>();
        ConcurrentSkipListMap<String,Human> HumansTree=new ConcurrentSkipListMap<>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            NodeList ListofHumans = doc.getElementsByTagName("Human");
            for (int i = 0; i < ListofHumans.getLength(); i++) {
                Element human = (Element) ListofHumans.item(i);
                NodeList ListNickNames = human.getElementsByTagName("nickname");
                NodeList ListSurname = human.getElementsByTagName("surname");
                NodeList ListAge=human.getElementsByTagName("age");
                NodeList ListDate=human.getElementsByTagName("date");
                NodeList ListLocation = human.getElementsByTagName("location");
                Element location = (Element) ListLocation.item(0);
                NodeList ListX = location.getElementsByTagName("x");
                NodeList ListY = location.getElementsByTagName("y");
                HumansTree.put(ListNickNames.item(0).getFirstChild().getNodeValue(),
                        new Human(ListNickNames.item(0).getFirstChild().getNodeValue(), ListSurname.item(0).getFirstChild().getNodeValue(),
                                new Location(Integer.parseInt(ListX.item(0).getFirstChild().getNodeValue()),Integer.parseInt(ListY.item(0).getFirstChild().getNodeValue())),
                                Integer.parseInt(ListAge.item(0).getFirstChild().getNodeValue()),new Date(ListDate.item(0).getFirstChild().getNodeValue())));
            }
        }
        catch(IllegalArgumentException|NullPointerException e){
            System.out.println("Неверное содержимое файла");
            System.exit(1);
        }
        catch (ParserConfigurationException e) {
            System.out.println("Сломали парсер, это браво");
            System.exit(1);
        } catch (SAXException | IOException l) {
            System.out.println("Неверное содержимое файла");
            System.exit(1);
        }
        //Output=output;
        System.out.println("Данные загружены");
        return HumansTree;
    }
    public static void XmlSaveFile(ConcurrentSkipListMap<String,Human> HumansTree) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element My = document.createElement("HumansTree");
            document.appendChild(My);
            for (Human human : HumansTree.values()) {
                Element Creature = document.createElement("Human");
                Element NickName = document.createElement("nickname");
                NickName.appendChild(document.createTextNode(human.getNickname()));
                Creature.appendChild(NickName);
                Element Surname = document.createElement("surname");
                Surname.appendChild(document.createTextNode(human.getSurName()));
                Creature.appendChild(Surname);
                Element Age = document.createElement("age");
                Age.appendChild(document.createTextNode(""+human.getAge()));
                Creature.appendChild(Age);
                Element Date = document.createElement("date");
                Date.appendChild(document.createTextNode(human.getDate().toGMTString()));
                Creature.appendChild(Date);
                Element Location=document.createElement("location");
                Element X=document.createElement("x");
                X.appendChild(document.createTextNode(""+human.getLocation().getX()));
                Element Y=document.createElement("y");
                Y.appendChild(document.createTextNode(""+human.getLocation().getY()));
                Location.appendChild(X);
                Location.appendChild(Y);
                Creature.appendChild(Location);
                My.appendChild(Creature);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(document);
            FileOutputStream fileOutputStream = new FileOutputStream(Output);
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            StreamResult result = new StreamResult(output);
            transformer.transform(domSource, result);
            System.out.println("Коллекция успешно сохранена в исходный файл");
            output.flush();
            output.close();
        } catch (IOException e) {
            System.out.println("Проблемы с файлом или нет доступа к файлу, данные не  сохранены");
        } catch (TransformerException|NullPointerException e) {
            System.out.println("Запись с ошибкой");
        } catch (ParserConfigurationException e) {
            System.out.println("Тут ничего не сделаешь, так сказать gg wp");
        }
    }
}
