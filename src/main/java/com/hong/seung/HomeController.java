package com.hong.seung;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hong.service.BoardPager;
import com.hong.service.FileUploadService;
import com.hong.service.UserService;
import com.hong.vo.BbsVO;
import com.hong.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	FileUploadService fileUploadService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/main")
	public ModelAndView main(Locale locale, Model model, HttpServletRequest request){
		System.out.println("-----HomeController main()-----");
		
		int rowcount = userService.rowCount();       
		System.out.println("rowcount >>> "+rowcount);
		model.addAttribute("rowcount",rowcount);
		
		ModelAndView mav = null;
		mav = new ModelAndView();		
		mav.setViewName("/main");
		
		return mav;
	}
	
	
	@RequestMapping(value="/login")
	public ModelAndView login(Locale locale, Model model){
		System.out.println("------HomeController login()------");
		
		ModelAndView mav = null;
		mav = new ModelAndView();		
		mav.setViewName("/login");
		
		return mav;
	}
	
	@RequestMapping(value="/loginAction")
	public ModelAndView loginAction(@ModelAttribute UserVO param, HttpServletRequest request
										, HttpServletResponse response, HttpSession session) throws IOException{
		System.out.println("------HomeController loginAction()------");
		
		UserVO uvo = new UserVO();
		uvo = userService.loginUser(param);
		System.out.println("uvo >>> : " + uvo);
		
		System.out.println(param.getUsername());
		
		ModelAndView mav = null;
		mav = new ModelAndView();
		PrintWriter out;
		if(uvo == null){
			try {
				mav.setViewName("main");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('ID/PW 를 확인해주십시요.');");
				out.println("history.back();");
				out.println("</script>");
				out.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			session.setAttribute("loginUser", uvo);
			mav.setViewName("main");
			return mav;
		}
		return mav; 
	}
	
	@RequestMapping(value="/logoutAction.do")
	public ModelAndView logout(HttpSession session){
		session.removeAttribute("loginUser");
			
		ModelAndView mav = null;
		mav = new ModelAndView();		
		mav.setViewName("redirect:/main.jsp");
			
		return mav;
	}
	
	@RequestMapping(value="/join")
	public ModelAndView join(@ModelAttribute UserVO param){
		System.out.println("-----HomeController join()-----");
		
		ModelAndView mav = null;
		mav = new ModelAndView();		
		mav.setViewName("/join");
		
		return mav;
	}
	
	@RequestMapping(value="/joinAction")
	public ModelAndView joinAction(@ModelAttribute UserVO param, HttpServletRequest request, HttpSession session){
		System.out.println("-----HomeController joinAction()-----");
		
		ArrayList userList = null;
		userList = new ArrayList();
		
		System.out.println("param.getUserEmail >>> : " + param.getUseremail());
		System.out.println("param.getUserPW >>> : " + param.getUserpw());
		System.out.println("param.getUserName >>> : " + param.getUsername());
		System.out.println("param.getUserGender >>> : " + param.getUsergender());
		
		try{
			int result = userService.insertUser(param);
			
			if(result > 0){
				System.out.println("회원가입성공");
			} else{
				System.out.println("회원가입실패");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("redirect:/login.do");
		return mav;
	}
	
	@RequestMapping(value="/bbsList1")
	public ModelAndView BBS1(Locale locale, Model model, HttpServletRequest request ) throws Exception{
		System.out.println("-----HomeController bbs1List()-----");
		int rowcount = userService.rowCount();       
		System.out.println("rowcount >>> "+rowcount);
		model.addAttribute("rowcount",rowcount);
		
		String searchOption = request.getParameter("searchOption");
		String keyword = request.getParameter("keyword");
		String curpage = request.getParameter("curpage");
		System.out.println("searchOption : " + searchOption);
		System.out.println("keyword : " + keyword);		
		System.out.println("curpage : " + curpage);
		int curpageint = Integer.parseInt(curpage);
		
		int countArticle = userService.countArticle(searchOption, keyword);   
		System.out.println("countArticle : " + countArticle );

		BoardPager boardPager= new BoardPager(countArticle,curpageint);
		int start = boardPager.getPageBegin();
		int end = boardPager.getPageEnd();
		int totblock = boardPager.getTotBlock();
		int totpage = boardPager.getTotPage();
		int prevpage = boardPager.getPrevPage();
		int nextpage = boardPager.getNextPage();
		System.out.println("pageBegin : " + start);
		System.out.println("pageEnd : " + end);
		System.out.println("totblock : " + totblock);
		System.out.println("totpage : " + totpage);
		System.out.println("prevpage : " + prevpage);
		System.out.println("nextpage : " + nextpage);
		
		List<BbsVO> list = userService.selectList(start, end, searchOption, keyword);
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("list", list);
		listMap.put("count", countArticle);
		listMap.put("searchOption", searchOption);
		listMap.put("keyword", keyword);
		listMap.put("boardPager", boardPager);

		System.out.println("curBlock : "+boardPager.getCurBlock());
		System.out.println("blockBegin : "+boardPager.getBlockBegin());
		System.out.println("blockEnd : "+boardPager.getBlockEnd());
		System.out.println();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("listMap", listMap );
		mav.setViewName("/bbs1");
		return mav;
	}
	
	@RequestMapping(value="/bbsContent.do")
	public ModelAndView bbsContent(Locale locale, Model model, HttpServletRequest request,
								HttpSession session, HttpServletResponse response, @RequestParam HashMap<String, String> map) throws Exception{
		System.out.println("-----HomeController bbsContent()------");

		int bNum = Integer.parseInt(request.getParameter("bNum"));
		System.out.println("bNum >>> : " + bNum);
		
		userService.increaseViewcnt(bNum);
		
		List<BbsVO> bList = userService.selectBbs(bNum);
		System.out.println("bList >>> : " + bList);
		
		System.out.println("글번호 : "+bList.get(0).getBno());
		System.out.println("작성자 : "+bList.get(0).getWriter());
		System.out.println("제목 : "+bList.get(0).getSubject());
		System.out.println("내용 : "+bList.get(0).getContent());
		System.out.println("작성날짜 : "+bList.get(0).getDate());
		System.out.println("조회수 : "+bList.get(0).getViewcnt());
		
		ModelAndView mav = null;
		mav = new ModelAndView();		
		mav.addObject("bNum", bNum);
		mav.addObject("bList", bList);
		mav.setViewName("/content");
		return mav;
	}
	
	@RequestMapping(value="/write.do")
	public ModelAndView write(Locale locale, Model model, HttpServletRequest request){
		System.out.println("-----HomeController write()-----");

		ModelAndView mav = null;
		mav = new ModelAndView();		
		mav.setViewName("/write");
		return mav;
	}
	
	@RequestMapping(value="/writeAction.do")
	public ModelAndView writeAction(@ModelAttribute BbsVO param, MultipartHttpServletRequest request, Model model){ 
		System.out.println("-----HomeController writeAction()-----");
		
		System.out.println("작성자 : "+param.getWriter());
		System.out.println("제목 : "+param.getSubject());
		System.out.println("내용 : "+param.getContent());
		System.out.println("날짜 : "+param.getDate());
		
		System.out.println("글번호 : " + param.getBno());
		
		List<MultipartFile> files = request.getFiles("file1");
		System.out.println("files : " + files);
		
		if(files.get(0).getSize()>0) { 
			for (MultipartFile multipartFile : files) {
				fileUploadService.restore(multipartFile);
			}
		}
		userService.insertBbs(param);
		
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("redirect:/bbsList1.do?curpage=1");
		return mav;
		
	}
	
	@RequestMapping(value="/delete.do")
	public ModelAndView delete(@ModelAttribute BbsVO param, HttpServletRequest request) {
		System.out.println("-----HomeController delete()-----");
		String delbbsno = request.getParameter("delbbsno");
		System.out.println("delbbsno : " + delbbsno);

		userService.deleteBbs(delbbsno);
		
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("redirect:/bbs1List.do?curpage=1");
		return mav;
	}
	
	@RequestMapping(value="/update.do")
	public ModelAndView update(@ModelAttribute BbsVO param, HttpServletRequest request){
		System.out.println("-------HomeController update()-------");
	
		int bNum = Integer.parseInt(request.getParameter("updatebbsno"));
		System.out.println("수정할 bNum >>> : " + bNum);
		
		List<BbsVO> bList = userService.selectBbs(bNum);
		System.out.println("수정할 bList >>> : " + bList);
		
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.addObject("bNum", bNum);
		mav.addObject("bList", bList);
		mav.setViewName("/update");
		return mav;
	}
	
	@RequestMapping(value="/updateAction.do")
	public ModelAndView updateAction(@ModelAttribute BbsVO param, HttpServletRequest request) {
		System.out.println("-----HomeController updateAction()-----");

		System.out.println("작성자 : "+param.getWriter());
		System.out.println("수정한 제목 : "+param.getSubject());
		System.out.println("수정한 내용 : "+param.getContent());
		System.out.println("수정한 날짜 : "+param.getDate());
		userService.updateBbs(param);
		
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("redirect:/bbsContent.do?bNum="+param.getBno());
		return mav;
	}
	
}
