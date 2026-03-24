# 🎧 Mini Spotify API

Projeto desenvolvido com Spring Boot para simular funcionalidades
básicas de uma plataforma de streaming de música, inspirado no Spotify.

------------------------------------------------------------------------

## 🚀 Como rodar a aplicação

### 🔹 Pré-requisitos

-   Java 17 ou superior
-   Maven (ou usar pelo IntelliJ)
-   IntelliJ IDEA (recomendado)

------------------------------------------------------------------------

### 🔹 Passo a passo

1.  Abra o projeto no IntelliJ
2.  Aguarde o Maven baixar as dependências
3.  Vá até a classe principal:

```{=html}
<!-- -->
```
    MiniSpotifyApplication.java

4.  Execute a aplicação (botão ▶️ ou Shift + F10)

------------------------------------------------------------------------

### 🔹 Após rodar

Você deve ver no terminal algo como:

    Tomcat started on port 8080

A API estará disponível em:

http://localhost:8080

------------------------------------------------------------------------

## 🧪 Como testar

Utilize o Postman para testar os endpoints.

Recomendado criar uma variável:

    baseUrl = http://localhost:8080

------------------------------------------------------------------------

## 📌 Principais endpoints

### 👤 Usuários

-   POST /usuarios
-   GET /usuarios
-   GET /usuarios/{id}
-   PUT /usuarios/{id}
-   DELETE /usuarios/{id}

### 🎤 Artistas

-   POST /artistas
-   GET /artistas
-   GET /artistas/{id}
-   PUT /artistas/{id}
-   DELETE /artistas/{id}

### 💿 Álbuns

-   POST /albuns
-   GET /albuns
-   GET /albuns/{id}
-   PUT /albuns/{id}
-   DELETE /albuns/{id}

### 🎵 Músicas

-   POST /musicas
-   GET /musicas
-   GET /musicas/{id}
-   PUT /musicas/{id}
-   DELETE /musicas/{id}
-   POST /musicas/{id}/reproduzir
-   GET /musicas/top10

### 📀 Playlists

-   POST /playlists
-   GET /playlists
-   GET /playlists/{id}
-   PUT /playlists/{id}
-   DELETE /playlists/{id}
-   POST /playlists/{id}/musicas/{idMusica}

### ⭐ Favoritos

-   POST /favoritos
-   GET /favoritos
-   GET /favoritos/{id}
-   DELETE /favoritos/{id}

------------------------------------------------------------------------

## ⚙️ Regras de negócio implementadas

-   Usuário inativo não pode reproduzir música
-   Reprodução incrementa o total de reproduções
-   Apenas o dono pode adicionar música na playlist
-   Não é permitido adicionar a mesma música duas vezes na playlist
-   Exclusão lógica (quando aplicável)
-   Top 10 músicas mais reproduzidas

------------------------------------------------------------------------

## 📦 Estrutura do projeto

-   Controller → recebe requisições HTTP
-   Service → regras de negócio
-   Classes → representam as entidades

------------------------------------------------------------------------

## 📁 Entidades

-   Usuario
-   Artista
-   Album
-   Musica
-   Playlist
-   Favorito

------------------------------------------------------------------------

## 📌 Observações

-   Os dados são armazenados em memória (HashMap)
-   Não utiliza banco de dados
-   IDs são gerados automaticamente
