package HookKiller.server.board.repository;

public interface ArticleInterface {
  Long getId();
  
  String getNickName();
  
  String getTitle();
  
  String getContent();
  int getLikeCount();
}
