package learning.spring.boot.rest.webservices.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import learning.spring.boot.rest.webservices.user.model.Post;

@Repository
public class PostDaoService {

	private static List<Post> allPostList = new ArrayList<Post>();

	private static Integer postIdCounter = 0;

	static {
		allPostList.add(new Post(++postIdCounter, "CEO"));
		allPostList.add(new Post(++postIdCounter, "CTO"));
		allPostList.add(new Post(++postIdCounter, "CFO"));
	}

	public List<Post> getAllPost() {
		return allPostList;
	}

	public Post retrivePost(int postId, List<Post> posList) {
		Post post = null;
		for (Post p : posList) {
			if (p.getPostId() == postId) {
				post = p;
			}
		}
		return post;
	}

	public Post addPost(Post newPost) {
		newPost.setPostId(++postIdCounter);
		allPostList.add(newPost);
		return newPost;
	}

	public boolean isPostExists(int postId) {
		Post post = this.retrivePost(postId, allPostList);
		if (post == null) {
			return false;
		}
		return true;
	}

}
