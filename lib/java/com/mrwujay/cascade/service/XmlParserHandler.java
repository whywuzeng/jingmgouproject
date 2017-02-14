package com.mrwujay.cascade.service;

import com.mrwujay.cascade.model.CityModel;
import com.mrwujay.cascade.model.DistrictModel;
import com.mrwujay.cascade.model.ProvinceModel;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParserHandler extends DefaultHandler
{
  CityModel cityModel = new CityModel();
  DistrictModel districtModel = new DistrictModel();
  private List<ProvinceModel> provinceList = new ArrayList();
  ProvinceModel provinceModel = new ProvinceModel();

  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if (paramString3.equals("district"))
      this.cityModel.getDistrictList().add(this.districtModel);
    do
    {
      return;
      if (paramString3.equals("city"))
      {
        this.provinceModel.getCityList().add(this.cityModel);
        return;
      }
    }
    while (!paramString3.equals("province"));
    this.provinceList.add(this.provinceModel);
  }

  public List<ProvinceModel> getDataList()
  {
    return this.provinceList;
  }

  public void startDocument()
    throws SAXException
  {
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    if (paramString3.equals("province"))
    {
      this.provinceModel = new ProvinceModel();
      this.provinceModel.setName(paramAttributes.getValue(0));
      this.provinceModel.setCityList(new ArrayList());
    }
    do
    {
      return;
      if (paramString3.equals("city"))
      {
        this.cityModel = new CityModel();
        this.cityModel.setName(paramAttributes.getValue(0));
        this.cityModel.setDistrictList(new ArrayList());
        return;
      }
    }
    while (!paramString3.equals("district"));
    this.districtModel = new DistrictModel();
    this.districtModel.setName(paramAttributes.getValue(0));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mrwujay.cascade.service.XmlParserHandler
 * JD-Core Version:    0.6.2
 */