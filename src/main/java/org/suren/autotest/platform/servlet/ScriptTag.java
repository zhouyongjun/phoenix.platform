/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.suren.autotest.platform.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author suren
 * @date 2017年1月31日 上午11:19:07
 */
public class ScriptTag extends TagSupport
{
	/**  */
	private static final long	serialVersionUID	= 1L;
	
	private String src;

	private HttpServletRequest	request;
	private JspWriter	out;
	
	private StringBuffer buf = new StringBuffer();
	
	@Override
	public void setPageContext(PageContext pageContext)
	{
		out = pageContext.getOut();
		request = (HttpServletRequest) pageContext.getRequest();
		super.setPageContext(pageContext);
	}

	@Override
	public void setParent(Tag t)
	{
		super.setParent(t);
	}

	@Override
	public int doStartTag() throws JspException
	{
		String basePath = request.getContextPath();
		
		buf.append("<script src=\"");
		buf.append(basePath);
		buf.append(src);
		buf.append("\"");
		buf.append("></script>");
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException
	{
		try
		{
			out.write(buf.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return super.doEndTag();
	}

	@Override
	public void release()
	{
		super.release();
	}

	/**
	 * @return the src
	 */
	public String getSrc()
	{
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(String src)
	{
		this.src = src;
	}

}
