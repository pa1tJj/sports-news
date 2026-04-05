package com.project.sport.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news")
@Getter
@Setter
public class NewsEntity extends BaseEntity{
	private static final long serialVersionUID = 1280203609783307289L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title",nullable = false, length = 255)
	private String title;

	@Column(name =  "summary",columnDefinition = "TEXT")
	private String summary;

	@Column(name = "slug", nullable = false, unique = true, length = 300)
	private String slug;
	 
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@Column(name = "thumbnail", length = 100)
	private String thumbnail;

	@Column(name = "views")
	private Integer views = 0;

	@Column(name = "metatitle")
	private String metaTitle;
	
	@Column(name = "metadescription")
	private String metaDescription;
	
	@Column(name = "metakeywords")
	private String metaKeywords;
	
	@Column(name = "isfeatured")
	private Boolean isFeatured = false;
	
	@Column(name = "priority")
	private Integer priority = 0;

	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name = "news_category",
	        joinColumns = @JoinColumn(name = "news_id"),
	        inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<CategoryEntity> categories;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name = "news_tag",
	           joinColumns = @JoinColumn(name = "news_id"),
	           inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<TagsEntity> tags;
	
	@OneToMany(mappedBy = "news", orphanRemoval = true)
	private List<CommentsEntity> comments = new ArrayList<CommentsEntity>();
	
}
