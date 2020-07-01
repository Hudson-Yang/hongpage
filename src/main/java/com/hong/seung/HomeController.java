package com.hong.seung;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import org.apache.commons.io.FilenameUtils;
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
import com.hong.vo.FileVO;
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
		mav.setViewName("redirect:/main.do");
			
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
	public ModelAndView BBS1(Locale locale, Model model, HttpServletRequest request) throws Exception{
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

		String bno = request.getParameter("bno");
		System.out.println("bno >>> : " + bno);
		
		userService.increaseViewcnt(bno);
		
		List<BbsVO> bList = userService.selectBbs(bno);
		System.out.println("bList : " + bList);
		System.out.println("글번호 : "+bList.get(0).getBno());
		System.out.println("작성자 : "+bList.get(0).getWriter());
		System.out.println("제목 : "+bList.get(0).getSubject());
		System.out.println("내용 : "+bList.get(0).getContent());
		System.out.println("작성날짜 : "+bList.get(0).getDate());
		System.out.println("조회수 : "+bList.get(0).getViewcnt());
		
		List<FileVO> fList = fileUploadService.selectFile(bno);
		if(!fList.isEmpty()) {
			int fc = fList.size();
			System.out.println("-첨부된 파일-");
			System.out.println("첨부파일 갯수 : "+fc);
			System.out.println("fList : "+fList);
			for(int c = 0 ; c <= (fc-1) ; c++) {
				System.out.println("파일번호 : "+fList.get(c).getFno());
				System.out.println("파일이름 : "+fList.get(c).getFogname());
				System.out.println("파일크기 : "+fList.get(c).getFsize()+" kB");
				System.out.println("파일형식 : "+FilenameUtils.getExtension(fList.get(c).getFogname()));
				System.out.println("파일위치 : "+fList.get(c).getFpath());
				System.out.println("--------");
			}
		}
		System.out.println();
		ModelAndView mav = null;
		mav = new ModelAndView();		
		mav.addObject("bno", bno);
		mav.addObject("bList", bList);
		mav.addObject("fList", fList);
		mav.setViewName("/content");
		return mav;
	}
	
	@RequestMapping(value="/fileDownload.do", method=RequestMethod.GET)
	public void filedownload(HttpServletResponse response,HttpServletRequest request) {
		System.out.println("-----HomeController fileDownload()------");
		
		String fno = request.getParameter("fno");
		System.out.println("다운로드 요청파일번호 : "+fno);
		FileVO fList = fileUploadService.downloadFile(fno);
		System.out.println(fList);
		System.out.println(fList.getFno());
		System.out.println(fList.getFsvname());
		System.out.println(fList.getFsize());
		System.out.println(FilenameUtils.getExtension(fList.getFsvname()));
		System.out.println(fList.getFpath());
		System.out.println();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fList.getFsvname() + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary"); 
        response.setHeader("Content-Type", FilenameUtils.getExtension(fList.getFsvname()));
        response.setHeader("Content-Length", "" + fList.getFsize());
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        try(FileInputStream fis = new FileInputStream(fList.getFpath());
        		OutputStream out = response.getOutputStream();){
        	int readcnt = 0;
        	byte[] buffer = new byte[1024];
        	while((readcnt = fis.read(buffer)) != -1) {
        		out.write(buffer,0,readcnt);
        	}
        }catch(Exception ex){
            throw new RuntimeException("file Save Error:::"+ex.getMessage());
        }
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
		
		List<MultipartFile> files = request.getFiles("file1");
		System.out.println("files : " + files);
		
		userService.insertBbs(param);
		System.out.println("글번호 : " + param.getBno());
		if(files.get(0).getSize()>0) { 
			for (MultipartFile multipartFile : files) {
				fileUploadService.restore(multipartFile,param.getBno());
			}
		}
		
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
		
		fileUploadService.deleteFile(delbbsno);
		userService.deleteBbs(delbbsno);
		
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("redirect:/bbsList1.do?curpage=1");
		return mav;
	}
	
	@RequestMapping(value="/update.do")
	public ModelAndView update(@ModelAttribute BbsVO param, HttpServletRequest request){
		System.out.println("-------HomeController update()-------");
	
		String bno = request.getParameter("updatebbsno");
		System.out.println("수정할 bno >>> : " + bno);
		
		List<BbsVO> bList = userService.selectBbs(bno);
		System.out.println("수정할 bList >>> : " + bList);
		
		List<FileVO> fList = fileUploadService.selectFile(bno);
		if(!fList.isEmpty()) {
			int fc = fList.size();
			System.out.println("-첨부된 파일-");
			System.out.println("첨부파일 갯수 : "+fc);
			System.out.println("fList : "+fList);
			for(int c = 0 ; c <= (fc-1) ; c++) {
				System.out.println("파일번호 : "+fList.get(c).getFno());
				System.out.println("파일이름 : "+fList.get(c).getFogname());
				System.out.println("파일크기 : "+fList.get(c).getFsize()+" kB");
				System.out.println("파일형식 : "+FilenameUtils.getExtension(fList.get(c).getFogname()));
				System.out.println("파일위치 : "+fList.get(c).getFpath());
				System.out.println("--------");
			}
		}
		System.out.println();
		
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.addObject("bno", bno);
		mav.addObject("bList", bList);
		mav.addObject("fList", fList);
		mav.setViewName("/update");
		return mav;
	}
	
	@RequestMapping(value="/updateAction.do")
	public ModelAndView updateAction(@ModelAttribute BbsVO param, MultipartHttpServletRequest request) {
		System.out.println("-----HomeController updateAction()-----");

		System.out.println("작성자 : "+param.getWriter());
		System.out.println("수정한 제목 : "+param.getSubject());
		System.out.println("수정한 내용 : "+param.getContent());
		System.out.println("수정한 날짜 : "+param.getDate());
		userService.updateBbs(param);
		
		List<MultipartFile> files = request.getFiles("file2");
		System.out.println("files : " + files);
		if(files.get(0).getSize()>0) { 
			for (MultipartFile multipartFile : files) {
				fileUploadService.restore(multipartFile,param.getBno());
			}
		}
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.setViewName("redirect:/bbsContent.do?bno="+param.getBno());
		return mav;
	}
	
}
