package com.precious.controller.admin;

import com.blade.annotation.Inject;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.precious.controller.BaseController;
import com.precious.model.Post;
import com.precious.service.PostService;
import com.precious.service.PostTagService;

public class FilmController extends BaseController {
	
	@Inject
	private PostService postService;
	
	@Inject
	private PostTagService postTagService;
	
	/**
	 * 添加/编辑电影页面
	 */
	public void film_page(Request request, Response response){
		Integer pid = request.paramAsInt("pid");
		if(null != pid){
			Post film = postService.getPost(pid);
			request.attribute("film", film);
		}
		response.render(this.getAdmin("film_page"));
	}
	
	/**
	 * 保存电影
	 */
	public void save_film(Request request, Response response){
		String title = request.query("title");
		String slug = request.query("slug");
		String cover = request.query("cover");
		Integer menu_id = request.queryAsInt("menu_id");
		String content = request.query("content");
		String links = request.query("links");
		String tags = request.query("tags");
		
		boolean flag = postService.save(title, slug, cover, menu_id, content, links, tags);
		
		if(flag){
			response.text(this.SUCCESS);
		} else {
			response.text(this.FAILURE);
		}
	}
	
	/**
	 * 修改电影
	 */
	public void update_film(Request request, Response response){
		Integer pid = request.queryAsInt("pid");
		String title = request.query("title");
		String slug = request.query("slug");
		String cover = request.query("cover");
		Integer menu_id = request.queryAsInt("menu_id");
		String content = request.query("content");
		String links = request.query("links");
		
		if(null == pid){
			return;
		}
		
		boolean flag = postService.update(pid, title, slug, cover, menu_id, content, links);
		
		if(flag){
			response.text(this.SUCCESS);
		} else {
			response.text(this.FAILURE);
		}
	}
	
	/**
	 * 删除电影
	 */
	public void delete_film(Request request, Response response){
		Integer pid = request.queryAsInt("pid");
		boolean flag = postService.delete(pid);
		if(flag){
			response.text(this.SUCCESS);
		} else {
			response.text(this.FAILURE);
		}
	}
	
	/**
	 * 删除电影标签
	 */
	public void delete_film_tag(Request request, Response response){
		Integer pid = request.queryAsInt("pid");
		Integer tid = request.queryAsInt("tid");
		if(null != pid && null != tid){
			boolean flag = postTagService.delete(pid, tid);
			if(flag){
				response.text(this.SUCCESS);
			} else {
				response.text(this.FAILURE);
			}
		}
	}
}
