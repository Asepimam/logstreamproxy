 package com.cms.tools.lib.services;
 
 import java.nio.ByteBuffer;
 import java.nio.CharBuffer;
 import java.nio.charset.CharacterCodingException;
 import java.nio.charset.Charset;
 import java.nio.charset.CharsetDecoder;
 import org.apache.logging.log4j.Logger;
 
 
 public class ServiceItem
 {
   private ItemTFP iTFP = null;
   protected PointDetail dPointSrc;
   protected ByteBuffer buf = null;
   
   protected Logger log;
   
   public ServiceItem(PointDetail dPointSrc, ByteBuffer buf, ItemTFP iTFP, String strLog) {
     this.iTFP = iTFP.duplicate();
     
     this.dPointSrc = dPointSrc;
     this.buf = buf;
     this.log = Logger.getLogger(strLog);
   }
   public ServiceItem() {}
   public ByteBuffer getBuf() throws NullPointerException {
     return this.buf;
   }
   public void setBuf(ByteBuffer buf) {
     this.buf = buf;
   }
   public int getBufLength() {
     if (this.buf == null) {
       return -1;
     }
     return this.buf.capacity();
   }
 
   
   public PointDetail getSource() {
     return this.dPointSrc;
   }
   public String itemToString() {
     String strMsg = null;
     Charset charset = Charset.forName("ISO-8859-1");
     CharsetDecoder decoder = charset.newDecoder();
     this.buf.clear();
     CharBuffer charBuffer = null;
     try {
       charBuffer = decoder.decode(this.buf);
     } catch (CharacterCodingException ex) {
       this.log.error(ex.getMessage());
     } 
     strMsg = charBuffer.toString();
     return strMsg;
   }
   
   public ItemTFP getItemTFP() throws NullPointerException {
     return this.iTFP;
   }
   
   public void setItemTFP(ItemTFP iTFP) {
     this.iTFP = iTFP;
   }
   public void setDPointSrc(PointDetail dPoint) {
     this.dPointSrc = dPoint;
   }
   public ItemSvc duplicate() {
     ItemSvc item = new ItemSvc();
     item.setDPointSrc(this.dPointSrc);
     item.log = this.log;
     item.setItemTFP(this.iTFP.duplicate());
     byte[] bytes = new byte[this.buf.capacity()];
     this.buf.clear();
     this.buf.get(bytes);
     this.buf.clear();
     ByteBuffer bufDup = ByteBuffer.wrap(bytes);
     item.setBuf(bufDup);
     
     return item;
   }
 }


