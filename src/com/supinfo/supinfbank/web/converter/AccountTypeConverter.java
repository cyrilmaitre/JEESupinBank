package com.supinfo.supinfbank.web.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.supinfo.supinfbank.dao.DaoFactory;
import com.supinfo.supinfbank.entity.AccountType;


@ManagedBean(name="accountTypeConverter")
@RequestScoped
public class AccountTypeConverter implements Converter
{
	@EJB private DaoFactory daoFactory;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		if(value != null)
		{
			try
			{
				return daoFactory.getAccountTypeDao().findById(Integer.parseInt(value));
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
    }

	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
    	if(value != null && value instanceof AccountType)
    		return String.valueOf(((AccountType) value).getIdAccountType());
    	else
    		return "";
    }
}
