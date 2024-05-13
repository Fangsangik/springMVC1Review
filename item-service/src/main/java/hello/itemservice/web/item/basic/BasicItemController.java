package hello.itemservice.web.item.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        //model에 collection 걸린다.
        return "basic/items";
    }

    /**
     * test용
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 같은 URL인데 Http method로 기능 구별
     */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String save() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(
            //thymeleaf에 있는 parameter 값으로 넘어온다.
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item); //저장된 결과물 바로 넣음

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(
            @ModelAttribute("item") Item item, Model model) {
        // @ModelAttribute("item") -> name 속성을 넣어줌
        // model.addAttribute("item", item);를 주석 처리 해줘도 된다. 자동으로 view에 넣어주는 역할도 한다.
        // model 또한 날려줘도 된다.

        itemRepository.save(item);

        //model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(
            @ModelAttribute Item item, Model model) {
        itemRepository.save(item);

        //클래스 명의 앞글자를 소문자로 변경 -> modelAttribute에 자동으로 담긴다.
        //model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(
            Item item) {
        itemRepository.save(item);

        //ModelAttribute 생략 가능
        //단순 타입 제외하고 임의의 객체들은 ModelAttribute 적용
        return "basic/item";
        //Post로 보여주고 상품 상세로 가서 끝.
        //새로 고침 할 경우 마지막으로 한 행위가 그대로 저장

        //redirect를 사용해서 -> 새로 다시 요청 함
        //마지막으로 상품 상세로 요청
    }

    //@PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item saveItem = itemRepository.save(item);

        //redirect와 관련된 요소들
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status",  true); //true -> 저장되서 넘어온 결과값이다 인식
        return "redirect:/basic/items/{itemId}"; //남는 애들은 쿼리 파라미터로 넘어간다.
    }

    //상품 수정
    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        //어떤 상품을 수정할지 id 값 넘어와야 한다.

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }
    //상품 수정 처리

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        //redirect를 하면 경로 변경
        return "redirect:/basic/items/{itemId}";
    }
}
